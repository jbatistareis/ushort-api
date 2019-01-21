package com.jbatista.ushort.api.services;

import com.jbatista.ushort.api.entities.Address;
import com.jbatista.ushort.api.entities.Stats;
import com.jbatista.ushort.api.repositories.AddressRepository;
import com.jbatista.ushort.api.repositories.ConfigurationRepository;
import java.util.zip.CRC32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlProcessor {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;

    private static final String charMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String regex = "^(http|https|ftp)(://).+";

    public Address process(String url) {
        if (!url.matches(regex)) {
            url = "http://" + url;
        }

        return addressRepository.save(new Address(shorten(url), url, configurationRepository.findById("1").get().getTtlHours()));
    }

    public String getFull(String id) {
        return addressRepository.findById(id).get().getFullUrl();
    }

    public Stats getStats() {
        final Stats stats = new Stats();

        addressRepository.findAllByOrderByFullUrl().forEach(address -> {
            stats.incrementReducedUrls();
            stats.incrementReducedLetters(Math.max(0, (address.getFullUrl().length() - 5)));
        });

        if (stats.getReducedUrls() > 0) {
            stats.setAverageUrlSize(stats.getReducedLeters() / stats.getReducedUrls());
            stats.setReductionPercentage(Math.round(((stats.getReducedLeters() * 100) / ((stats.getReducedLeters() + (5 * stats.getReducedUrls()))))));
        }

        return stats;
    }

    // when using CRC32 as id collisions can occur, but since entries have an expiration date and the probability is like 1% after tens of thousands, the risk is irrelevant
    public String shorten(String url) {
        final CRC32 crc32 = new CRC32();
        crc32.update(url.getBytes());

        final StringBuilder sbCrcValue = new StringBuilder(String.valueOf(crc32.getValue()));
        final StringBuilder sbShortUrl = new StringBuilder();

        while (sbCrcValue.length() != 10) {
            sbCrcValue.insert(0, '0');
        }

        for (int i = 0; i <= 8; i += 2) {
            sbShortUrl.append(charMap.charAt(Integer.parseInt(sbCrcValue.substring(i, i + 2)) % 62));
        }

        return sbShortUrl.toString();
    }

}

package br.ufrn.imd.songs.config;

import br.ufrn.imd.songs.service.SongPopularityService;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitEventListenerTopic implements CommandLineRunner {

    @Autowired
    private RedissonClient redisClient;

    @Autowired
    private SongPopularityService songPopularityService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting topic event listener...");
        RTopic songPopularityTopic = redisClient.getTopic("songPopularityTopic", StringCodec.INSTANCE);
        songPopularityTopic.addListener(String.class, (channel, msg) -> {
            System.out.println("Canal- " + channel + " e msg- " + msg);
            songPopularityService.increasePopularity(msg);
        });
    }
}

package com.se.sample;

import com.se.sample.entity.Speaker;
import com.se.sample.repository.SpeakerRepository;

public class SpeakerDataBuilder {

    private final SpeakerRepository speakerRepository;

    private Speaker speaker;

    public SpeakerDataBuilder(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public SpeakerDataBuilder speaker(String name) {
        this.speaker = Speaker.builder().name(name).build();
        return this;
    }

    public SpeakerDataBuilder company(String company) {
        this.speaker.setCompany(company);
        return this;
    }

    public Speaker build() {
        return this.speaker;
    }

    public Speaker save() {
        return this.speakerRepository.save(this.speaker);
    }


}

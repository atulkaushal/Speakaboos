package com.wfhackathon2022.speakaboos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wfhackathon2022.speakaboos.entity.PronunciationPreferences;

public interface PronunciationPreferencesRepository extends JpaRepository<PronunciationPreferences, Integer> {

}

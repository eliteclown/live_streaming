package com.karthik.visionlabs.services;


import com.karthik.visionlabs.entities.LiveSession;
import com.karthik.visionlabs.dtos.createStreamResponseDTO;
import com.karthik.visionlabs.entities.User;
import com.karthik.visionlabs.entities.enums.Status;
import com.karthik.visionlabs.repositories.LiveSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final CallService zenClient;
    private final LiveSessionRepository liveSessionRepository;

    public LiveSession createStream(String title, User instructor, LocalDateTime schedule, int duration , long courseId){

        // call streamApi to create stream

        createStreamResponseDTO resp = zenClient.createStream(title, instructor.getName(), schedule, duration);

        //Build and save  local entity
        LiveSession stream = new LiveSession();
        stream.setStreamId(resp.getStreamId());
        stream.setTitle(title);
        stream.setUser(instructor);
        stream.setScheduledAt(schedule);
        stream.setStatus(Status.valueOf(resp.getStatus()));
        stream.setStreamUrl(resp.getStreamUrl());
        return liveSessionRepository.save(stream);

    }

    public LiveSession startStream(Long id){
        LiveSession  stream = liveSessionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Stream Not Found"));

        zenClient.startStream(stream.getStreamId());
        stream.setStatus(Status.LIVE);
        return liveSessionRepository.save(stream);
    }

    public LiveSession stopStream(Long id){
        LiveSession  stream = liveSessionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Stream Not Found"));
        zenClient.stopStream(stream.getStreamId());
        stream.setStatus(Status.ENDED);
        return liveSessionRepository.save(stream);
    }

    public LiveSession scheduleStream(Long id , LocalDateTime newSchedule){
        // If ZenStream has a specific schedule endpoint, call it here. Otherwise, recreate stream or update.
        LiveSession  stream = liveSessionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Stream Not Found"));
        // For simplicity, assume ZenStream allows updating schedule via create API or similar:
        // E.g. call createStream API with new schedule/time (details depend on ZenStream API design)
        // Then update local record:
        stream.setScheduledAt(newSchedule);
        stream.setStatus(Status.SCHEDULED);
        return liveSessionRepository.save(stream);
    }
    public Optional<String> getEmbedUrl(Long id) {
        return liveSessionRepository.findById(id).map(LiveSession::getStreamUrl);
    }

    public List<LiveSession> listStreamsByCourse(Long courseId) {
        return liveSessionRepository.findByCourseId(courseId);
    }
    public List<LiveSession> listStreamsByInstructor(String instructor) {
        return liveSessionRepository.findByInstructor(instructor);
    }
}

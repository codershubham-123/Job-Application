package com.skywins.Job.Application.job.impl;

import com.skywins.Job.Application.job.Job;
import com.skywins.Job.Application.job.JobRepository;
import com.skywins.Job.Application.job.JobService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class JobServiceimpl implements JobService {
  JobRepository jobRepository;


  public JobServiceimpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<Job> findAll() {

    //        return jobs;
    return jobRepository.findAll();
  }

  @Override
  public void createJob(Job job) {
    //        job.setId(nextId++);
    //        jobs.add(job);
    jobRepository.save(job);
  }

  @Override
  public Job getJobById(Long id) {
    return jobRepository.findById(id).orElse(null);
  }

  @Override
  public boolean deleteJobById(Long id) {
    try {
      jobRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean updateJob(Long id, Job updatedJob) {
    Optional<Job> jobOptional = jobRepository.findById(id);
    if (jobOptional.isPresent()) {
      Job job = jobOptional.get();
      job.setTitle(updatedJob.getTitle());
      job.setDescription((updatedJob.getDescription()));
      job.setMinSalary(updatedJob.getMinSalary());
      job.setMaxSalary(updatedJob.getMaxSalary());
      job.setLocation(updatedJob.getLocation());
      jobRepository.save(job);
      return true;
    }
    return false;
  }
}

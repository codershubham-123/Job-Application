package com.skywins.Job.Application.job;

import com.skywins.Job.Application.common.ApiResponse;
import com.skywins.Job.Application.job.dto.JobResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class JobController {
  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping("/jobs")
  public ResponseEntity<ApiResponse<List<JobResponse>>> findAll() {
    List<JobResponse> jobs = jobService.findAll().stream().map(JobResponse::from).toList();
    return ResponseEntity.ok(ApiResponse.of(jobs, jobs.size()));
  }

  @PostMapping("/jobs")
  public ResponseEntity<String> createJob(@RequestBody Job job) {
    System.out.println("Inside Controller");
    jobService.createJob(job);
    return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
  }

  @GetMapping("/jobs/{id}")
  public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
    Job job = jobService.getJobById(id);

    if (job != null) return new ResponseEntity<>(JobResponse.from(job), HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/jobs/{id}")
  public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
    boolean deleted = jobService.deleteJobById(id);
    if (deleted) {
      return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/jobs/{id}")
  //    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
  public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
    boolean updated = jobService.updateJob(id, updatedJob);
    if (updated) {
      return new ResponseEntity<>("Job Updated Successfully!", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

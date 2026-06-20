package com.skywins.Job.Application.company;

import com.skywins.Job.Application.common.ApiResponse;
import com.skywins.Job.Application.company.dto.CompanyResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAllCompanies() {
    List<CompanyResponse> companies =
        companyService.getAllCompanies().stream().map(CompanyResponse::from).toList();
    return new ResponseEntity<>(ApiResponse.of(companies, companies.size()), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
    boolean updated = companyService.updateCompany(company, id);
    if (updated) {
      return new ResponseEntity<>("Company Updated Sucessfully", HttpStatus.OK);
    }
    return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
  }

  @PostMapping()
  public ResponseEntity<String> createCompany(@RequestBody Company company) {
    companyService.createCompany(company);
    return new ResponseEntity<>("Company Added Successfully", HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
    boolean isDeleted = companyService.deleteCompanyById(id);
    if (isDeleted) {
      return new ResponseEntity<>("Company Successfully Deleted", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long id) {
    Company company = companyService.getCompanyById(id);
    if (company != null) {
      return new ResponseEntity<>(CompanyResponse.from(company), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

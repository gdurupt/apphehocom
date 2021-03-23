import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Site } from '../interfaces/site';
import { User } from '../interfaces/user';
import { SitesService } from '../services/sites.service';
import { UserbysiteService } from '../services/userbysite.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  searchform : FormGroup;

  OneSite: Site;
  sites: Site[];
  user: User;


  errorForm = false;

  constructor(
    private siteService: SitesService,
    private userBySite: UserbysiteService,
    private userService: UsersService,
    private router: Router,
    private formbuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('tokenhehocom')){
    }else{
      this.router.navigateByUrl('');
    }
    this.getAllSiteByUser();
    this.getUser();
    this.initSearchForm();
  }


  getAllSiteByUser(){
    this.userBySite.getOneUser().subscribe({
      next: data => {
        this.sites = data;

      },
      error: error => {
        
      }
  });
  }

  getUser(){
    this.userService.getTokenUser().subscribe({
      next: data => {
        this.user = data;
      },
      error: error => {
      }
  });
  }

  initSearchForm(){
    this.searchform = this.formbuilder.group({
      name: ['',Validators.required]
    })
  }

  searchSiteName(){
    this.siteService.getOneSiteByName(this.searchform.get('name').value).subscribe({
      next: data => {
          this.OneSite = data[0];
          this.errorForm = false;
          if(this.OneSite == null){
            this.errorForm = true;
          }
      },
      error: error => {
        
      }
  });
  }

}

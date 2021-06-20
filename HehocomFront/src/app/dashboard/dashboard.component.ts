import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Mission } from '../interfaces/mission';
import { Site } from '../interfaces/site';
import { User } from '../interfaces/user';
import { ComentsService } from '../services/coments.service';
import { MissionsService } from '../services/missions.service';
import { SitesService } from '../services/sites.service';
import { UserbysiteService } from '../services/userbysite.service';
import { UsersService } from '../services/users.service';
import * as $ from 'jquery';
import { faFileUpload, faTrashAlt, faTools } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  searchform : FormGroup;

  siteForm : FormGroup;

  OneSite: Site;
  sites: Site[];
  user: User;



  NameFile = 'Ajouter fichier';
  file;
  checkFile = true;

  uploadId = 0;

  deleteId = 0;
  nameOfSite;

  faFileUpload = faFileUpload;
  faTrash = faTrashAlt;
  faWrench = faTools;

  errorForm = false;

  constructor(
    private siteService: SitesService,
    private userBySite: UserbysiteService,
    private userService: UsersService,
    private router: Router,
    private formbuilder: FormBuilder,
    private comentService: ComentsService,
    private missionService: MissionsService
  ) { }

  ngOnInit(): void {
    if(sessionStorage.getItem('tokenhehocom')){
    }else{
      this.router.navigateByUrl('');
    }
    this.getAllSiteByUser();
    this.getUser();
    this.initSearchForm();
    this.initSiteForm();
  }


  getAllSiteByUser(){
    this.userBySite.getOneUser().subscribe({
      next: data => {
        this.sites = data;
        for (let index = 0; index < this.sites.length; index++) {
          this.missionService.getallMissionRequeteClientBySite(this.sites[index].id).subscribe({
            next: data => {
              this.sites[index].requeteClient = data.length;
            },
            error: error => {
            }
          });

          this.missionService.getLastMissionBySite(this.sites[index].id).subscribe({
            next: data => {
              if(data != null){
                this.sites[index].lastMission = data.name + ' ajouté le ' + data.dateCreation;
              }
            },
            error: error => {
              this.sites[index].lastMission = 'Aucune mission pour le moment';
            }
          });

          this.missionService.getCountMissionBySite(this.sites[index].id).subscribe({
            next: data => {
              this.sites[index].NumberMission = data;
            },
            error: error => {
              this.sites[index].NumberMission = 0;
            } 
        });

        this.comentService.getLastComentBySite(this.sites[index].id).subscribe({
          next: data => {
            if(data != null){
              this.sites[index].lastComment = 'Par ' + data.nameUser + ' ajouté le ' + data.dateCreation;
            }
          },
          error: error => {
            this.sites[index].lastComment = 'Aucun commentaire pour le moment'
          }
        });

        this.comentService.getCountComentBySite(this.sites[index].id).subscribe({
          next: data => {
            this.sites[index].NumberComent = data;
          },
          error: error => {
            this.sites[index].NumberComent = 0;
          } 
      });
          
        }

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
          this.OneSite = data;
          this.errorForm = false;
          if(data == null || data == undefined){
            this.errorForm = true;
          }
        if(this.OneSite != undefined){
          this.missionService.getLastMissionBySite(this.OneSite.id).subscribe({
            next: data => {
              if(data != null){
                this.OneSite.lastMission = data.name + ' ajouté le ' + data.dateCreation;
              }
            },
            error: error => {
              this.OneSite.lastMission = 'Aucune mission pour le moment';
            }
          });

          this.missionService.getCountMissionBySite(this.OneSite.id).subscribe({
            next: data => {
              this.OneSite.NumberMission = data;
            },
            error: error => {
              this.OneSite.NumberMission = 0;
            } 
          });

          this.comentService.getLastComentBySite(this.OneSite.id).subscribe({
          next: data => {
            if(data != null){
              this.OneSite.lastComment = 'Par ' + data.nameUser + ' ajouté le ' + data.dateCreation;
            }
          },
          error: error => {
            this.OneSite.lastComment = 'Aucun commentaire pour le moment'
          }
          });

          this.comentService.getCountComentBySite(this.OneSite.id).subscribe({
          next: data => {
            this.OneSite.NumberComent = data;
          },
          error: error => {
            this.OneSite.NumberComent = 0;
          } 
          });
        }
      },
      error: error => {
        
      }
  });
  }

  initSiteForm(){
    this.siteForm = this.formbuilder.group({
      name: ['',Validators.required],
      statut: ['',Validators.required],
      type: ['',Validators.required],
      url: ['',Validators.required],
      bdd_name:[''],
      date_creation:['']
    })
  }

  addSite(){
    $('#ajoutsite').modal('hide');
    this.siteService.addSite(this.siteForm.value).subscribe({
      next: data => {
        this.getAllSiteByUser();
      },
      error: error => {
      }
  });
  }


  update(site: Site){
    this.uploadId = site.id;

    this.siteForm.get('name').setValue(site.name);
    this.siteForm.get('statut').setValue(site.statut);
    this.siteForm.get('type').setValue(site.type);
    this.siteForm.get('url').setValue(site.url);
    this.siteForm.get('bdd_name').setValue(site.bdd_name);
    this.siteForm.get('date_creation').setValue(site.dateCreation);
  }

  updateSite(){
    $('#update').modal('hide');
    this.siteService.updateSite(this.uploadId,this.siteForm.value).subscribe({
      next: data => {
        this.getAllSiteByUser();
        if(this.searchform.get('name').value != ''){
          this.searchSiteName()
        }
      },
      error: error => {
      }
    });
  }
  
  clearForm(){
    this.siteForm.get('name').setValue('');
    this.siteForm.get('statut').setValue('');
    this.siteForm.get('type').setValue('');
    this.siteForm.get('url').setValue('');
    this.siteForm.get('bdd_name').setValue('');
  }

  idDelete(site : Site){
    this.deleteId = site.id;
    this.nameOfSite = site.name;
  }

  deleteSite(){
    $('#supprimer').modal('hide');
    this.siteService.deleteSite(this.deleteId).subscribe({
      next: data => {
        this.getAllSiteByUser();
        if(this.searchform.get('name').value != null){
          this.searchSiteName()
        }
      },
      error: error => {
      }
    });
  }

  changefile(event){
    this.file = <File>event.target.files[0];
    this.NameFile = this.file.name
    this.checkFile = false;
  }

  updateFile(){
    $('#fichier').modal('hide');
    this.siteService.AddFileBySite(this.uploadId,this.file).subscribe({
      next: data => {
        this.getAllSiteByUser();
        if(this.searchform.get('name').value != null){
          this.searchSiteName()
        }
        this.NameFile = 'Ajouter fichier';
      },
      error: error => {
      }
    });
  }
}

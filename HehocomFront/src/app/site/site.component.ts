import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Coment } from '../interfaces/coment';
import { Mission } from '../interfaces/mission';
import { Site } from '../interfaces/site';
import { User } from '../interfaces/user';
import { ComentsService } from '../services/coments.service';
import { MissionsService } from '../services/missions.service';
import { SitesService } from '../services/sites.service';
import { UsersService } from '../services/users.service';
import * as $ from 'jquery';
import { faFileUpload, faArrowRight, faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { ServicesService } from '../services/services.service';
import { Service } from '../interfaces/service';
import { ServiceBySiteService } from '../services/service-by-site.service';



@Component({
  selector: 'app-site',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.scss']
})
export class SiteComponent implements OnInit {

  user: User;
  OneSite: Site;
  missions: Mission[];
  coments:Coment[];
  servicesOnSite: Service[];
  services: Service[];

  idSite;

  missionSearch: FormGroup;
  missionForm: FormGroup;
  comentForm: FormGroup;
  serviceForm:FormGroup;

  idMission = 0;
  idComent = 0;

  uploadFile = false;
  NameFile = 'Ajouter fichier';
  file;

  nextArrowComent;
  nextArrowMission;

  faArrowRight = faArrowRight;
  faArrowLeft = faArrowLeft;
  faFileUpload = faFileUpload;

  offsetMission = 0;
  offsetComent = 0;

  constructor(
    private userService: UsersService,
    private siteService: SitesService,
    private route: ActivatedRoute,
    private missionService: MissionsService,
    private comentService: ComentsService,
    private formbuilder: FormBuilder,
    private router: Router,
    private serviceBySiteService: ServiceBySiteService,
    private serviceService: ServicesService
  ) { }

  ngOnInit(): void {
    this.initAllForm();
    this.getUser();
    this.getSite(this.route.snapshot.paramMap.get('name'));
  }

  initAllForm(){
    this.missionSearch = this.formbuilder.group({
      statut:['REQUETECLIENT',Validators.required]
    });

    this.serviceForm = this.formbuilder.group({
      idService:['',Validators.required],
      idSite:''
    });

    this.missionForm = this.formbuilder.group({
      content:['',Validators.required],
      idSite:	'',
      statut:['',Validators.required],
      type:['',Validators.required],
      name:['',Validators.required],
    });

    this.comentForm = this.formbuilder.group({
      content: ['',Validators.required],
      idSite:	''
    });
  }

  addService(){
    this.serviceForm.get('idSite').setValue(this.OneSite.id);
    this.serviceBySiteService.AddServiceBySite(this.serviceForm.value).subscribe({
      next: data => {
        this.getServiceByIdSite();
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

  getComent(){
    this.comentService.getComentBySite(this.idSite,this.offsetComent).subscribe({
      next: data => {
        this.nextArrowComent = true;
        this.coments = data;
        if(data.length != 6){
          this.nextArrowComent = false;
        }
      },
      error: error => {
      }
  });
  }

  getMission(){
    this.missionService.getMissionBySite(this.idSite,this.missionSearch.get('statut').value,this.offsetMission).subscribe({
      next: data => {
        this.nextArrowMission = true;
        this.missions = data;
        if(data.length != 6){
          this.nextArrowMission = false;
        }
      },
      error: error => {
      }
  });
  }

  getServiceByIdSite(){
    this.serviceBySiteService.getServiceBySite(this.OneSite.id).subscribe({
      next: data => {
        this.servicesOnSite = data;
        this.getAllService();
      },
      error: error => {
      }
  });
  }

  removeService(id){
    this.serviceBySiteService.deleteIdService(id,this.OneSite.id).subscribe({
      next: data => {
        this.getServiceByIdSite()
      },
      error: error => {
      }
  });
  }

  getAllService(){
    this.serviceService.getAllService().subscribe({
      next: data => {
        for (let index = 0; index < data.length; index++) {
          for (let kindex = 0; kindex < this.servicesOnSite.length; kindex++) {
            if(this.servicesOnSite[kindex].id == data[index].id){
              data.splice(index, 1);
            }         
          }  
        }
        this.services = data;
      },
      error: error => {
      }
  });
  }


  getSite(name){
    this.siteService.getOneSiteByName(name).subscribe({
      next: data => {
        if(data == null){
          this.router.navigateByUrl('');
        }

          this.OneSite = data;
          this.idSite = data.id;
          this.getMission();
          this.getComent();
          this.getServiceByIdSite();
          this.missionForm.get('idSite').setValue(data.id);
          this.comentForm.get('idSite').setValue(data.id);
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

  addMission(){
    this.idMission = 0;

  }

  addComent(){
    this.idComent = 0;
    this.comentForm.get('content').setValue('');
  }

  changefile(event){
    this.file = <File>event.target.files[0];
    this.NameFile = this.file.name
  }

  coment(){
    console.log(this.idComent);
    if(this.idComent != 0){
      this.comentService.UpdateComentBySite(this.comentForm.value,this.idComent).subscribe({
        next: data => {
          this.getComent();
          $('#coment').modal('hide');
        },
        error: error => {
        }
    });
    }else{
      this.comentService.AddComentBySite(this.comentForm.value).subscribe({
        next: data => {
          this.getComent();
          $('#coment').modal('hide');
        },
        error: error => {
        }
    });
    }
    $('#coment').modal('hide');
  }

  updateComent(coment: Coment){
    if(coment.idUser != this.user.id){
      return null;
    }
    this.idComent = coment.id;
    this.comentForm.get('content').setValue(coment.content);
    $('#coment').modal('show');
  }

  mission(){
    
    if(this.idMission != 0){
      this.missionService.UpdateMissionBySite(this.missionForm.value,this.idMission).subscribe({
        next: data => {
          this.getMission();
          $('#mission').modal('hide');
        },
        error: error => {
        }
    });
    }else{
      this.missionService.AddMissionBySite(this.missionForm.value).subscribe({
        next: data => {
          this.getMission();
          $('#mission').modal('hide');
        },
        error: error => {
        }
    });
    }
    $('#mission').modal('hide');
  }

  updateMission(mission: Mission){
    if(mission.idUser != this.user.id){
      return null;
    }
    this.idMission = mission.id;
    this.missionForm.get('content').setValue(mission.content);
    this.missionForm.get('name').setValue(mission.name);
    this.missionForm.get('statut').setValue(mission.statut);
    this.missionForm.get('type').setValue(mission.type);
    $('#mission').modal('show');
  }

  trueFile(){
    if(this.uploadFile ==  true){
      this.uploadFile = false;
    }else{
      this.uploadFile = true;
    }
  }

  backComent(){
    this.offsetComent = this.offsetComent - 1;
    this.getComent();
  }

  nextComent(){
    this.offsetComent = this.offsetComent + 1;
    this.getComent();
  }

  backMission(){
    this.offsetMission = this.offsetMission - 1;
    this.getMission();
  }

  nextMission(){
    this.offsetMission = this.offsetMission + 1;
    this.getMission();
  }

  deleteComent(){
    this.comentService.deleteComentBySite(this.idComent).subscribe({
      next: data => {
        this.getComent();
        $('#coment').modal('hide');
      },
      error: error => {
      }
  });
  }

  deleteMission(){
    this.missionService.deleteMissionBySite(this.idMission).subscribe({
      next: data => {
        this.getMission();
        $('#mission').modal('hide');
      },
      error: error => {
      }
  });
  }


  checkUser(idUser){
    if(idUser == this.user.id){
      return 'cardPointer';
    }else{
      return '';
    }

  }

  checkLots(lot){

    if(this.user.status != 'CLIENT' && this.OneSite.lots < lot){
      return 'cardPointer';
    }else if(this.OneSite.lots < lot){
      return '';
    }else if(this.user.status == 'CLIENT'){
      return "circle-success";
    }else{
      return "circle-success cardPointer";
    }
  }

  changeLots(lot){
    if(this.user.status != 'CLIENT'){
      this.siteService.updateLot(this.idSite,lot).subscribe({
        next: data => {
         this.OneSite.lots = lot;
        },
        error: error => {
        }
    });
    }
  }

}

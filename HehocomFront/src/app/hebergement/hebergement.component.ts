import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Hebergement } from '../interfaces/hebergement';
import { User } from '../interfaces/user';
import { HebergementsService } from '../services/hebergements.service';
import { UsersService } from '../services/users.service';
import * as $ from 'jquery';
import { SitesService } from '../services/sites.service';
import { Router } from '@angular/router';
import { formatDate} from '@angular/common';

@Component({
  selector: 'app-hebergement',
  templateUrl: './hebergement.component.html',
  styleUrls: ['./hebergement.component.scss']
})
export class HebergementComponent implements OnInit {

  userCo: User;

  searchform: FormGroup;
  addForm: FormGroup;
  addSiteH: FormGroup;

  hebergements: Hebergement[];
  hebergementSearch: Hebergement;

  idDelete;
  nameDelete;

  idUpdate;

  errorForm = false;
  errorAddForm = false;
  errorAddSiteToH = false;


  now = new Date();
  nowFormatted: string;
  tableDate;

  constructor(
    private userService: UsersService,
    private herbergermentService: HebergementsService,
    private formbuilder: FormBuilder,
    private siteService: SitesService,
    private router: Router
  ) {
    this.nowFormatted = formatDate(this.now, 'dd/MM/yyyy', 'en-US');
   }

  ngOnInit(): void {
    this.getUser();
    this.initSearchForm();
    this.getAllHebergement();
    this.initAddForm();
  }

  getUser(){
    this.userService.getTokenUser().subscribe({
      next: data => {
        this.userCo = data;
        if(this.userCo.status == 'CLIENT' || data == null){
          this.router.navigateByUrl('');
        }
      },
      error: error => {
      }
  });
  }

  getAllHebergement(){
    this.herbergermentService.getHebergementBySite().subscribe({
      next: data => {
        this.hebergements = data;

        this.tableDate = this.nowFormatted.split('/');

        for (let index = 0; index < this.hebergements.length; index++) {
          this.siteService.getAllSiteByHebergement(this.hebergements[index].id).subscribe({
            next: data => {
              for (let index = 0; index < data.length; index++) {
                let tableDateSite = data[index].dateCreation.split('/');
                if(tableDateSite[1] == this.tableDate[1]){
                  data[index].datealert = true;
                }else{
                  data[index].datealert = false;
                }
              }
              this.hebergements[index].sites = data;
            },
            error: error => {
            }
        });
          
        }
      },
      error: error => {
      }
  });
  }


  clearForm(){
    this.addForm.get('ip').setValue('');
    this.addForm.get('type').setValue('');
    this.addForm.get('nameHberger').setValue('');
    this.addForm.get('hebergeur').setValue('');
  }

  
  initSearchForm(){
    this.searchform = this.formbuilder.group({
      name: ['',Validators.required]
    })

    this.addSiteH = this.formbuilder.group({
      siteName: ['',Validators.required]
    })
  }

  initAddForm(){
    this.addForm = this.formbuilder.group({
      hebergeur: ['',Validators.required],
      ip: ['',Validators.required],
      nameHberger: ['',Validators.required],
      type: ['',Validators.required]
    })
  }

  searchHebergement(){
    this.herbergermentService.getHebergementByName(this.searchform.get('name').value).subscribe({
      next: data => {
        this.hebergementSearch = data;
        this.tableDate = this.nowFormatted.split('/');
        this.siteService.getAllSiteByHebergement(this.hebergementSearch.id).subscribe({
          next: data => {
            for (let index = 0; index < data.length; index++) {
              let tableDateSite = data[index].dateCreation.split('/');
              if(tableDateSite[1] == this.tableDate[1]){
                data[index].datealert = true;
              }else{
                data[index].datealert = false;
              }
            }
            this.hebergementSearch.sites = data;
          },
          error: error => {
          }
      });
      },
      error: error => {
      }
  });
  }

  addH(){
    this.herbergermentService.AddHebergementBySite(this.addForm.value).subscribe({
      next: data => {
        if(data == null){
          this.errorAddForm = true;
        }else{
          this.getAllHebergement();
          this.errorAddForm = false;
          $('#ajoutH').modal('hide');
          if(this.searchform.get('name').value != ''){
            this.searchHebergement();
          }
        }
      },
      error: error => {
      }
  });
  }

  addSiteHebergement(id){
    this.siteService.getOneSiteByName(this.addSiteH.get('siteName').value).subscribe({
      next: data => {
        if(data == null){
          this.errorAddSiteToH = true;
        }else{
          this.errorAddSiteToH = false;
          this.addSiteToH(id,data.id);
        }
      },
      error: error => {
      }
  });
  }

  addSiteToH(idH,idSite){
    this.siteService.updateHebergementSite(idSite,idH).subscribe({
      next: data => {
          this.addSiteH.get('siteName').setValue('');
          this.getAllHebergement();
          if(this.searchform.get('name').value != ''){
            this.searchHebergement();
          }
      },
      error: error => {
      }
  });
  }

  updateH(){
    this.herbergermentService.UpdateHebergementBySite(this.addForm.value,this.idUpdate).subscribe({
      next: data => {
        if(data == null){
          this.errorAddForm = true;
        }else{
          this.getAllHebergement();
          this.errorAddForm = false;
          $('#modifierUser').modal('hide');
          if(this.searchform.get('name').value != ''){
            this.searchHebergement();
          }
        }
      },
      error: error => {
      }
  });
  }


  updateModal(hebergement: Hebergement){
    this.idUpdate = hebergement.id;

    this.addForm.get('ip').setValue(hebergement.ip);
    this.addForm.get('type').setValue(hebergement.type);
    this.addForm.get('nameHberger').setValue(hebergement.nameHberger);
    this.addForm.get('hebergeur').setValue(hebergement.hebergeur);
  }

  deleteModal(hebergementToDelete : Hebergement){
    this.idDelete = hebergementToDelete.id;
    this.nameDelete = hebergementToDelete.nameHberger;
  }

  deleteH(){
    this.herbergermentService.deleteHebergementBySite(this.idDelete).subscribe({
      next: data => {
        this.getAllHebergement();
        $('#supprimerHebergment').modal('hide');
      },
      error: error => {
      }
  });
  }

}

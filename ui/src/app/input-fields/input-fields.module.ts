import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { OrderModule } from 'ngx-order-pipe';
import { NgSelectModule } from '@ng-select/ng-select';

import { TitleSelectionComponent } from './title-selection/title-selection.component';
import { GenderSelectionComponent } from './gender-selection/gender-selection.component';
import { SchoolSelectionComponent } from './school-selection/school-selection.component';
import { ContactTypeSelectionComponent } from './contact-type-selection/contact-type-selection.component';
import { LegalSexSelectionComponent } from './legal-sex-selection/legal-sex-selection.component';

@NgModule({
  declarations: [
    TitleSelectionComponent,
    LegalSexSelectionComponent,
    GenderSelectionComponent,
    SchoolSelectionComponent,
    ContactTypeSelectionComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    OrderModule,
    NgSelectModule
  ],
  exports: [
    TitleSelectionComponent,
    LegalSexSelectionComponent,
    GenderSelectionComponent,
    SchoolSelectionComponent,
    ContactTypeSelectionComponent
  ]
})
export class InputFieldsModule { }

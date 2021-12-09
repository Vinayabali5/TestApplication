import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbDatepickerModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FilterPipeModule } from 'ngx-filter-pipe';
import { OrderModule } from 'ngx-order-pipe';

import { InputFieldsModule } from '../input-fields/input-fields.module';

import { ApplicationComponent } from './application.component';

import { SubFormsModule } from '../sub-forms/sub-forms.module';
import { IntroductionBlurbComponent } from './introduction-blurb/introduction-blurb.component';
import { PersonalDetailsFormComponent } from './personal-details-form/personal-details-form.component';
import { ContactDetailsFormComponent } from './contact-details-form/contact-details-form.component';
import { SchoolDetailsFormComponent } from './school-details-form/school-details-form.component';
import { CourseSelectionFormComponent } from './course-selection-form/course-selection-form.component';


@NgModule({
    declarations: [
        ApplicationComponent,
        IntroductionBlurbComponent,
        PersonalDetailsFormComponent,
        ContactDetailsFormComponent,
        SchoolDetailsFormComponent,
        CourseSelectionFormComponent
    ],
    imports: [
        CommonModule,
        InputFieldsModule,
        FormsModule,
        ReactiveFormsModule,
        SubFormsModule,
        OrderModule,
        FilterPipeModule,
        NgbModule,
        NgbDatepickerModule
    ],
    exports: [ApplicationComponent]
})
export class ApplicationModule { }

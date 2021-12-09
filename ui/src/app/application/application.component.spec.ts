import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { AppRoutingModule } from '../app-routing.module';
import { ContactTypeSelectionComponent } from '../input-fields/contact-type-selection/contact-type-selection.component';
import { GenderSelectionComponent } from '../input-fields/gender-selection/gender-selection.component';
import { InputFieldsModule } from '../input-fields/input-fields.module';
import { LegalSexSelectionComponent } from '../input-fields/legal-sex-selection/legal-sex-selection.component';
import { SchoolSelectionComponent } from '../input-fields/school-selection/school-selection.component';
import { TitleSelectionComponent } from '../input-fields/title-selection/title-selection.component';
import { ApiConfigService } from '../service/api-config.service';
import { AddressFormComponent } from '../sub-forms/address-form/address-form.component';
import { SubFormsModule } from '../sub-forms/sub-forms.module';

import { ApplicationComponent } from './application.component';
import { ContactDetailsFormComponent } from './contact-details-form/contact-details-form.component';
import { CourseSelectionFormComponent } from './course-selection-form/course-selection-form.component';
import { IntroductionBlurbComponent } from './introduction-blurb/introduction-blurb.component';
import { PersonalDetailsFormComponent } from './personal-details-form/personal-details-form.component';
import { SchoolDetailsFormComponent } from './school-details-form/school-details-form.component';

describe('ApplicationComponent', () => {
    let component: ApplicationComponent;
    let fixture: ComponentFixture<ApplicationComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [
                ApplicationComponent,
                IntroductionBlurbComponent,
                PersonalDetailsFormComponent,
                ContactDetailsFormComponent,
                SchoolDetailsFormComponent,
                CourseSelectionFormComponent,
            ],
            imports: [
                HttpClientModule,
                AppRoutingModule,
                ReactiveFormsModule,
                SubFormsModule,
                InputFieldsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ApplicationComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

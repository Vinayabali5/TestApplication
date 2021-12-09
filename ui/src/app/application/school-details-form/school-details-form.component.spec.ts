import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ControlContainer, FormBuilder, FormsModule, ReactiveFormsModule, ValidationErrors } from '@angular/forms';
import { NgSelectComponent, NgSelectModule } from '@ng-select/ng-select';
import { InputFieldsModule } from 'src/app/input-fields/input-fields.module';
import { SchoolSelectionComponent } from 'src/app/input-fields/school-selection/school-selection.component';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { SubFormsModule } from 'src/app/sub-forms/sub-forms.module';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { SchoolDetailsFormComponent } from './school-details-form.component';

describe('SchoolDetailsFormComponent', () => {
    let component: SchoolDetailsFormComponent;
    let fixture: ComponentFixture<SchoolDetailsFormComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [
                SchoolDetailsFormComponent,
                SchoolSelectionComponent,
                //NgSelectComponent
            ],
            imports: [
                HttpClientModule,
                ReactiveFormsModule,
                NgSelectModule,
                SubFormsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(SchoolDetailsFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });


    it('should be invalid when the form is blank', () => {
        expect(component.schoolForm.valid).toBeFalsy();
    });

    it('should be invalid when the school is blank', () => {
        let errors: ValidationErrors = {};
        let school = component.schoolForm.controls['school'];
        expect(school.valid).toBeFalsy();

        errors = school.errors || {};
        expect(errors).toBeTruthy();
        expect(errors['required']).toBeTruthy();
    });

    it('should be valid when required fields are supplied (without manual school entry)', () => {
        const sampleData = {
            school: 10,
            endDate: '30/07/2022',
            schoolNotListed: false
        }
        component.schoolForm.patchValue(sampleData);

        fixture.detectChanges();

        expect(component.schoolForm.valid).toEqual(true);
        expect(component.schoolForm.value).toEqual(sampleData);
    });

    it('should be valid when required fields are supplied (with manual school entry)', () => {
        console.log('SchoolDetailsFormComponent - should be valid when required fields are supplied (with manual entry)');
        console.log(component);

        component.schoolForm.patchValue({ schoolNotListed: true });

        component.toggleNotListed();

        fixture.detectChanges();

        component.schoolForm.patchValue({
            schoolName: 'Test School',
            endDate: '30/07/2022',
            schoolNotListed: true,
            schoolAddress: {
                search: null,
                selectedAddress: null,
                line1: 'Some Street',
                line2: null,
                line3: null,
                town: 'Some Town',
                county: 'Some County',
                postcode: 'RH2 0SD'
            }
        });

        fixture.detectChanges();

        expect(component.schoolForm.valid).toBe(true);
    });

});

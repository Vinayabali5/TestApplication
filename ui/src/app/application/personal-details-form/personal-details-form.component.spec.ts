import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { GenderSelectionComponent } from 'src/app/input-fields/gender-selection/gender-selection.component';
import { InputFieldsModule } from 'src/app/input-fields/input-fields.module';
import { LegalSexSelectionComponent } from 'src/app/input-fields/legal-sex-selection/legal-sex-selection.component';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { AddressFormComponent } from 'src/app/sub-forms/address-form/address-form.component';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { PersonalDetailsFormComponent } from './personal-details-form.component';

describe('PersonalDetailsComponent', () => {
    let component: PersonalDetailsFormComponent;
    let fixture: ComponentFixture<PersonalDetailsFormComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [
                PersonalDetailsFormComponent,
                LegalSexSelectionComponent,
                GenderSelectionComponent,
                AddressFormComponent
            ],
            imports: [
                HttpClientModule,
                ReactiveFormsModule,
                InputFieldsModule
            ]
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(PersonalDetailsFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

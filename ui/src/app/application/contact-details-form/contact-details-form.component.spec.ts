import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { ContactTypeSelectionComponent } from 'src/app/input-fields/contact-type-selection/contact-type-selection.component';
import { InputFieldsModule } from 'src/app/input-fields/input-fields.module';
import { TitleSelectionComponent } from 'src/app/input-fields/title-selection/title-selection.component';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { AddressFormComponent } from 'src/app/sub-forms/address-form/address-form.component';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { ContactDetailsFormComponent } from './contact-details-form.component';

describe('ContactDetailsFormComponent', () => {
    let component: ContactDetailsFormComponent;
    let fixture: ComponentFixture<ContactDetailsFormComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [
                ContactDetailsFormComponent,
                ContactTypeSelectionComponent,
                TitleSelectionComponent,
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
        fixture = TestBed.createComponent(ContactDetailsFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});

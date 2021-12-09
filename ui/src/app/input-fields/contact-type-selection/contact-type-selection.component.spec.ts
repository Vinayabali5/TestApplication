import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { ContactTypeService } from 'src/app/service/data/contact-type.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { MockDataService } from 'src/testing/mock-data.service';
import { ContactTypeSelectionComponent } from './contact-type-selection.component';

describe('ContactTypeSelectionComponent', () => {
    let component: ContactTypeSelectionComponent;
    let fixture: ComponentFixture<ContactTypeSelectionComponent>;
    let dataStub: ContactTypeService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            declarations: [ContactTypeSelectionComponent],
            imports: [HttpClientModule]
        }).overrideComponent(ContactTypeSelectionComponent, {
            set: {
                providers: [
                    { provide: ContactTypeService, useClass: MockDataService }
                ]
            }
        })
            .compileComponents();
        TestBed.inject(ContactTypeService);
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(ContactTypeSelectionComponent);
        component = fixture.componentInstance;
        dataStub = fixture.debugElement.injector.get(ContactTypeService);
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should load data from the data service', () => {
        const testData = [{id:1, code: 'T', description: 'Test A'}];
        const spy = spyOn(dataStub, 'getAll').and.returnValue(
            of(testData)
        );
        component.ngOnInit();
        expect(component.data).toEqual(testData);
        expect(spy.calls.any()).toEqual(true);
    });

});

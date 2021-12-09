import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NgSelectModule } from '@ng-select/ng-select';
import { of } from 'rxjs';
import { School } from 'src/app/model/school';
import { ApiConfigService } from 'src/app/service/api-config.service';
import { SchoolService } from 'src/app/service/data/school.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { MockDataService } from 'src/testing/mock-data.service';

import { SchoolSelectionComponent } from './school-selection.component';

describe('SchoolSelectionComponent', () => {
    let component: SchoolSelectionComponent;
    let fixture: ComponentFixture<SchoolSelectionComponent>;
    let dataStub: SchoolService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                { provide: SchoolService, useClass: MockDataService }
            ],
            declarations: [SchoolSelectionComponent],
            imports: [
                HttpClientModule,
                NgSelectModule
            ]
        }).overrideComponent(SchoolSelectionComponent, {
            set: {
                providers: [
                    { provide: SchoolService, useClass: MockDataService }
                ]
            }
        })
            .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(SchoolSelectionComponent);
        component = fixture.componentInstance;
        dataStub = fixture.debugElement.injector.get(SchoolService);
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should load data from the data service', () => {
        const testData: School[] = [{id:1, name: 'Test School', address: 'Some Address', partner: true}];
        const spy = spyOn(dataStub, 'getAll').and.returnValue(
            of(testData)
        );
        component.ngOnInit();
        expect(component.data).toEqual(testData);
        expect(spy.calls.any()).toEqual(true);
    });

});

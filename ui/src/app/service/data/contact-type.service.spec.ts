import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';
import { asyncData } from 'src/testing/async-observable-helpers';

import { ContactType } from 'src/app/model/contact-type';
import { ContactTypeService } from './contact-type.service';
import { doesNotReject } from 'assert';

describe('ContactTypeService', () => {
    let httpClientSpy: { get: jasmine.Spy };

    let service: ContactTypeService;

    beforeEach(() => {
        httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);

        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService },
                { provide: HttpClient, useValue: httpClientSpy}
            ],
            imports: []
        });
        service = TestBed.inject(ContactTypeService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should return expected ContactTypes', () => {
        const expected: ContactType[] = [{ id: 1, code: 'M', description: 'Mr' }, { id: 2, code: 'N', description: 'Mrs' }]
        httpClientSpy.get.and.returnValue(asyncData(expected))

        service.getAll().subscribe(data => {
            expect(data).toEqual(expected, 'Expected Data');
        });
        expect(httpClientSpy.get.calls.count()).toBe(1, 'one call');
    });

});

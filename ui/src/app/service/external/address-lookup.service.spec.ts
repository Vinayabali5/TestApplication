import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';

import { AddressLookupService } from './address-lookup.service';

describe('AddressLookupService', () => {
    let service: AddressLookupService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(AddressLookupService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

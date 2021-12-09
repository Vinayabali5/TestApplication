import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';

import { BasicApiService } from './basic-api.service';

describe('BasicApiService', () => {
    let service: BasicApiService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(BasicApiService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

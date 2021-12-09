import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';
import { ApiConfigService } from '../api-config.service';

import { AccessService } from './access.service';

describe('AccessServiceService', () => {
    let service: AccessService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule,
                AppRoutingModule
            ]
        });
        service = TestBed.inject(AccessService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

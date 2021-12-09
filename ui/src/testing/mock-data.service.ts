import { Observable, of } from 'rxjs/';
import { LookupDataService } from '../app/service/data/lookup-data-service';

export class MockDataService implements LookupDataService<any[]> {

    private mockData = [{
        id: 1,
        code: 'A',
        description: 'Test A'
    },{
        id: 2,
        code: 'B',
        description: 'Test B'
    }]

    public getAll(): Observable<any[]> {
        return of(this.mockData);
    }
}

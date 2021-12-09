import { Observable } from "rxjs";

export interface LookupDataService<T> {

    getAll(): Observable<T>;

}

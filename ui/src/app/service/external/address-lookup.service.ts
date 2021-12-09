import { Injectable } from '@angular/core';
import { LoqateLookupValue } from 'src/app/model/loqate/lookup-value';
import { LoqateAddress } from 'src/app/model/loqate/loqate-address';
import { resourceLimits } from 'worker_threads';

@Injectable({
    providedIn: 'root'
})
export class AddressLookupService {

    private DEBUG = false;

    private LOQATE_KEY = 'JC38-KK31-JT96-JA91';

    private loqate;

    addressList: any[] = [];

    constructor() {
        this.loqate = require('@pcs/loqate')(this.LOQATE_KEY);
        if (this.DEBUG) console.log("Address Lookup Service");
        if (this.DEBUG) console.log(this.loqate);
    }

    private processResults(result: LoqateLookupValue[], existing: any[] = []): any[] {
        let addressList: any[] = [];
        result.forEach((element: LoqateLookupValue) => {
            if (element.Type == "Postcode") {
                let results = (async () => {
                    const result = await this.loqate.capture.container(element.Id);
                    if (this.DEBUG) console.log("Load Container Results");
                    if (this.DEBUG) console.log(result);
                    return this.processResults(result, existing);
                })();
                //this.loadContainer(element.Id).then((res) => addressList = addressList.concat(this.processResults(res)));
            } else if (element.Type == "Address") {
                addressList.push(element);
            }
        });
        return existing.concat(addressList);
    }

    lookup(value: string) {
        if (this.DEBUG) console.log("Address Lookup: " + value);
        let addressList: any[] = [];
        this.find(value).then((res) => {
            addressList = this.processResults(res)

        });
    }

    find(value: string): Promise<LoqateLookupValue[]> {
        return (async (): Promise<LoqateLookupValue[]> => {
            const result = await this.loqate.capture.find(value);
            if (this.DEBUG) console.log("Find Results");
            if (this.DEBUG) console.log(result);
            return result;
        })();
    }

    loadContainer(id: string, existing: any[] = []): Promise<LoqateLookupValue[]> {
        return (async () => {
            const result = await this.loqate.capture.container(id);
            if (this.DEBUG) console.log("Load Container Results");
            if (this.DEBUG) console.log(result);
            return result;
        })();
    }

    loadAddress(id: string): Promise<LoqateAddress[]> {
        return (async () => {
            const result = await this.loqate.capture.address(id)
            if (this.DEBUG) console.log(result)
            return result;
        })();
    }
}

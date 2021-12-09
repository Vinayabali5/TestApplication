import { Component, EventEmitter, forwardRef, Input, OnDestroy, OnInit, Output } from '@angular/core';
import {
    AbstractControl,
    ControlValueAccessor,
    FormBuilder,
    FormControl,
    FormGroup,
    NG_VALIDATORS,
    NG_VALUE_ACCESSOR,
    Validators,
} from '@angular/forms';
import { Subscription } from 'rxjs';

import { Address } from 'src/app/model/address';
import { LoqateLookupValue } from 'src/app/model/loqate/lookup-value'
import { AddressLookupService } from 'src/app/service/external/address-lookup.service';
import { Utils } from 'src/app/utils';

@Component({
    selector: 'address-form',
    templateUrl: './address-form.component.html',
    styleUrls: ['./address-form.component.sass'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => AddressFormComponent),
            multi: true,
        },
        {
            provide: NG_VALIDATORS,
            useExisting: forwardRef(() => AddressFormComponent),
            multi: true,
        },
    ],
})
export class AddressFormComponent implements ControlValueAccessor, OnInit, OnDestroy {

    DEBUG = false;

    form: FormGroup;
    subscriptions: Subscription[] = [];

    showResults = false;


    @Input() idSuffix: any = '';

    onChange: any = (_: Address) => { };
    onTouch: any = () => { };

    @Output() onCreate: EventEmitter<any> = new EventEmitter();
    constructor(private fb: FormBuilder, private addressLookupService: AddressLookupService) {
        // Create the form
        this.form = this.fb.group({
            search: [],
            selectedAddress: [],
            line1: [null, Validators.required],
            line2: [null],
            line3: [null],
            town: [null, Validators.required],
            county: [null],
            postcode: [null, Validators.required],
        });

        this.subscriptions.push(
            // any time the inner form changes update the parent of any change
            this.form.valueChanges.subscribe((value) => {
                this.onChange(value);
                this.onTouch();
            })
        );
    }

    ngOnInit() {
        this.onCreate.emit(this.form)
    }


    private sortAddressList(a: LoqateLookupValue, b: LoqateLookupValue) {
        return (a.Text > b.Text) ? 1 : -1;
    }

    addressList: any[] = [];
    //selectedAddress?: LoqateLookupValue;

    search(event: Event) {
        const controlName = 'search';
        const value: string = this.form.controls[controlName].value;
        this.addressList = [];
        if (value.length >= 3) {
            if (this.DEBUG) console.log("Searching for: " + value);
            this.lookup(value);
        } else {
            this.showResults = false;
        }
    }

    detectAddressSelect(event: Event) {
        const value: LoqateLookupValue = this.form.controls['selectedAddress'].value as LoqateLookupValue;
        this.loadAddress(value);
    }

    lookup(value: string) {
        this.addressLookupService.find(value).then(
            (result) => {
                if (this.DEBUG) console.log('Lookup Complete: ');
                if (this.DEBUG) console.log(result);
                this.addressList = result
            }
        ).finally(() => {
            this.addressList.sort(this.sortAddressList);
            if (this.addressList.length !== 0) {
                this.showResults = true;
            }
        });
    }

    loadAddress(value: LoqateLookupValue) {
        if (this.DEBUG) console.log('Load Address from value: ');
        if (this.DEBUG) console.log(value);
        if (value.Type == "Address") {
            if (this.DEBUG) console.log("Loqate Address");
            this.addressLookupService.loadAddress(value.Id).then((res) => {
                if (this.DEBUG) console.log("Loaded Address: ");
                if (res !== null) {
                    let address: any = {
                        search: null,
                        line1: res[0].Line1 !== "" ? res[0].Line1 : null,
                        line2: res[0].Line2 !== "" ? res[0].Line2 : null,
                        line3: res[0].Line3 !== "" ? res[0].Line3 : null,
                        town: res[0].City !== "" ? res[0].City : null,
                        county: res[0].ProvinceName !== "" ? res[0].ProvinceName : null,
                        postcode: res[0].PostalCode !== "" ? res[0].PostalCode : null
                    }
                    this.form.patchValue(address);
                }
            }).finally(() => {
                this.showResults = false;
            });
        } else {
            if (this.DEBUG) console.log("Loqate Lookup Value");
            this.addressLookupService.loadContainer(value.Id).then((res) => {
                this.addressList = res.sort(Utils.sortAddressLookup);
                this.form.patchValue({ selectedAddress: null });
            }).finally(() => {
                this.showResults = true;
            });
        }
    }

    // Getters and Setters

    get value(): Address {
        return this.form.value;
    }

    set value(value: Address) {
        this.form.setValue(value);
        this.onChange(value);
        this.onTouch();
    }

    // Interface implementations

    ngOnDestroy(): void {
        this.subscriptions.forEach((s) => s.unsubscribe());
    }

    writeValue(value: null | Address): void {
        if (value) {
            this.value = value;
        }

        if (value === null) {
            this.form.reset();
        }
    }

    registerOnChange(fn: () => {}): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: (_: Address) => {}): void {
        this.onTouch = fn;
    }

    // communicate the inner form validation to the parent form
    validate(_: FormControl) {
        return this.form.valid ? null : { addressIncomplete: { message: 'The address is not complete.' } };
    }
}

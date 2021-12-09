import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { InputFieldsModule } from '../input-fields/input-fields.module';

import { AddressFormComponent } from './address-form/address-form.component';


@NgModule({
    declarations: [
        AddressFormComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        InputFieldsModule
    ],
    exports: [
        AddressFormComponent
    ]
})
export class SubFormsModule { }

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { AdminService } from '../../services/admin.service';
import { ToastrService } from 'ngx-toastr';

interface Supplier {
    supplierId: number;
    supplierName: string[];
    supplierEmail: string[];
    supplierContact: string[];
    supplierAddress: string[];
}

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit{
  form: FormGroup;
  isCustomerDataAdded: Boolean = false;
  supplierDataSource = new MatTableDataSource<Supplier>();
  supplierDisplayedColumns: string[] = ['position', 'name', 'email', 'contact', 'address'];
  supplierCounter: number = 1;

  constructor(
    private fb: FormBuilder,
    private service: AdminService,
    private toast: ToastrService,
    private formBuilder: FormBuilder,
  ) {
    this.form = this.fb.group({
      supplierId: [''],
      supplierName: [''],
      supplierEmail: [''],
      supplierContact: [''],
      supplierAddress: [''],
    });
  }

  supplier = {
    supplierId : "",
    supplierName : "",
    supplierEmail : "",
    supplierContact : "",
    supplierAddress : "",
  }

  ngOnInit(): void {
    this.service.getLastSupplier().subscribe(res=>{
      if(res!==null){
        this.supplier = res;
      }
    });

    // Form validation
    this.form = this.formBuilder.group({
      supplierName: ["", [Validators.required]],
      supplierEmail: ["", [Validators.required]],
      supplierContact: ["", [Validators.required]],
      supplierAddress: ["", [Validators.required]]
    })

  }


  addCustomer() {
    const newSupplier: Supplier = {
      supplierId: this.supplierCounter++,
      supplierName: this.form.get('supplierName')?.value,
      supplierEmail: this.form.get('supplierEmail')?.value,
      supplierContact: this.form.get('supplierContact')?.value,
      supplierAddress: this.form.get('supplierAddress')?.value,
    };

    this.supplierDataSource.data = [...this.supplierDataSource.data, newSupplier]; // Refresh data source
    this.resetForm();
    this.isCustomerDataAdded = true;
  }

  resetForm() {
    this.form.reset();
  }

  resetTableData(){
    this.supplierDataSource.data = [];
  }

  finalData: { suppliers: Supplier[] } = { suppliers: [] };

  submitForm(){
    this.finalData.suppliers = this.supplierDataSource.data;

    console.log(JSON.stringify(this.finalData));

    this.service.addSupplier(this.finalData).subscribe(res=>{
      if(res!==null){
        this.toast.success("You have added supplier/s in your inventory.","Supplier Added.");
        this.ngOnInit();
        this.supplierDataSource.data = [];
      }
    },err=>{
      this.toast.info("Some listed suppliers already added. Please check and try again.","Supplier Info.");
    });
  }

}

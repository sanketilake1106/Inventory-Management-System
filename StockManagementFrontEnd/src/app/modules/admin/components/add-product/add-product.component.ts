import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  // imageUrl: string | ArrayBuffer | null = "../../../../../assets/dist/img/frame.png";
  constructor(
    private service: AdminService,
    private formBuilder: FormBuilder,
    private toast: ToastrService,
    private router: Router
  ) { }

  // selectedFileName: string = '';
  // myFile: File | undefined;

  form: FormGroup = new FormGroup({
    productName: new FormControl(''),
    productHSNNo: new FormControl(''),
    productDescription: new FormControl(''),
    productCategory: new FormControl(''),
    productCost: new FormControl(''),
    // file: new FormControl(''),
  });

  products = {
    productId: "",
    productName: "",
    productHSNNo: "",
    productDescription: "",
    productCategory: "",
    productCost: "",
    productDate: "",
    // productImage: ""
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      productName: ["", [Validators.required]],
      productHSNNo: ["", [Validators.required]],
      productDescription: ["", [Validators.required]],
      productCategory: ["", [Validators.required]],
      productCost: ["", [Validators.required]],
      // file: ["", [Validators.required]],
    });

    this.service.getLastProduct().subscribe(res => {
      if (res !== null) {
        this.products = res;
      }
    });
  }

  // onFileSelected(event: any) {
  //   const file: File = event.target.files[0];
  //   this.selectedFileName = file ? file.name : '';
  //   this.myFile = file;
  //   this.previewImage(file);
  // }

  // previewImage(file: File) {
  //   const reader = new FileReader();
  //   reader.readAsDataURL(file);
  //   reader.onload = () => {
  //     this.imageUrl = reader.result;
  //   };
  // }

  resetForm() {
    this.form.reset();
  }

  formSubmit() {
    // alert(JSON.stringify(this.form.value));
    if (this.form.valid) {
      this.service.addProduct(this.form.value).subscribe(res => {
        this.toast.success('Product added successfully:', 'Product Added');
        this.form.reset();
        this.router.navigate(['/admin-dashboard/show-data']);
      }, error => {
        this.toast.error('Error adding product:', "Failed!");
      });
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { Papa } from 'ngx-papaparse';
import { AdminService } from '../../services/admin.service';


export interface ProductsElements {
  productId: number;
  productName: string;
  productHSNNo: string;
  productDescription: string;
  productCategory: string;
  productCost: number;
  productDate: string;
}


@Component({
  selector: 'app-show-data',
  templateUrl: './show-data.component.html',
  styleUrls: ['./show-data.component.css']
})
export class ShowDataComponent implements OnInit {
  displayedColumns: string[] = ['productId', 'productName', 'productHSNNo',
     'productDescription', 'productCategory', 'productDate', 'productCost'];
  imageUrl: string | ArrayBuffer | null = "../../../../../assets/dist/img/frame.png";
  products: ProductsElements[] = [];
  constructor(private papa: Papa, private service: AdminService) { }
  dataSource = new MatTableDataSource(this.products);
  ngOnInit(): void {
    this.service.getAllProducts().subscribe(res => {
      if (res !== null) {
        this.products = res;
        this.dataSource.data = res;
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  calculateProductNetAmount(){
    let totalNetAmount = 0;
    this.dataSource.filteredData.forEach(element => {
      totalNetAmount += element.productCost;
    });
    return totalNetAmount;
  }

  downloadPDF() {
    const doc = new jsPDF();
    doc.text('Product List', 10, 10);
    (doc as any).autoTable({
      head: [['No.', 'Product Name', 'Product HSN', 'Description', 'Category', 'Price', 'Date']],
      body: this.dataSource.data.map(element => [
        element.productId,
        element.productName,
        element.productHSNNo,
        element.productDescription,
        element.productCategory,
        element.productCost,
        element.productDate
      ])
    });
    doc.save('product-list.pdf');
  }

  downloadCSV() {
    const csvData = this.dataSource.data.map(element => ({
      No: element.productId,
      Name: element.productName,
      HSN: element.productHSNNo,
      Description: element.productDescription,
      Category: element.productCategory,
      Price: element.productCost,
      Date: element.productDate
    }));
    const csv = this.papa.unparse(csvData);
    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', 'product-list.csv');
    link.style.visibility = 'hidden';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  printTable() {
    const printContent = document.getElementsByTagName('table')[0].outerHTML;
    const originalContents = document.body.innerHTML;
    document.body.innerHTML = printContent;
    window.print();
    document.body.innerHTML = originalContents;
    window.location.reload();
  }
}

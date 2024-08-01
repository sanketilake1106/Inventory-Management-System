import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { Papa } from 'ngx-papaparse';
import { AdminService } from '../../services/admin.service';

interface CustomerElements {
  position: number;
  customerId: number;
  customerName: string;
  customerEmail: string;
  customerContact: number;
  customerAddress: string;
}

let CUSTOMER_DATA: CustomerElements[] = [];

@Component({
  selector: 'app-show-customer',
  templateUrl: './show-customer.component.html',
  styleUrls: ['./show-customer.component.css']
})
export class ShowCustomerComponent implements OnInit {
  displayedColumns: string[] = ['position', 'customerName', 'customerEmail', 'customerContact', 'customerAddress'];
  dataSource = new MatTableDataSource(CUSTOMER_DATA);

  constructor(private papa: Papa, private service: AdminService) { }

  ngOnInit(): void {
    this.service.getAllCustomers().subscribe({
      next: (res: CustomerElements[]) => {
        this.dataSource.data = res;
        this.dataSource.data.forEach((p, index) => p.position = index + 1);
      }
    });

    console.log(this.dataSource.data)

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  downloadPDF() {
    const doc = new jsPDF();
    doc.text('Customer List', 10, 10);
    (doc as any).autoTable({
      head: [['No.', 'Customer ID', 'Name', 'Email', 'Contact', 'Address']],
      body: this.dataSource.data.map(element => [
        element.position,
        element.customerId,
        element.customerName,
        element.customerEmail,
        element.customerContact,
        element.customerAddress,
      ])
    });
    doc.save('customer-list.pdf');
  }

  downloadCSV() {
    const csvData = this.dataSource.data.map(element => ({
      'No.': element.position,
      'Customer ID': element.customerId,
      'Name': element.customerName,
      'Email': element.customerEmail,
      'Contact': element.customerContact,
      'Address': element.customerAddress,
    }));
    const csv = this.papa.unparse(csvData);
    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', 'customer-list.csv');
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


  prods = [
    {
      "products": [
          {
              "productId": 0,
              "productHSNNo": "",
              "productName": "",
              "productDescription": "",
              "productCategory": "",
              "productCost": 0.0,
              "productDate": ""
          }
      ],
      "saleProductQty": 0,
      "totalAmount" : 0.0,
    }
  ]


  totalNetAmount = 0;
  openDialog(customerId: any) {
    this.totalNetAmount = 0;
    this.service.getAllSaleProductByCustomerId(customerId).subscribe({
      next: (res) => {
        this.prods = res;
        this.prods.forEach((element) => {
          this.totalNetAmount += element.totalAmount;
          });
      }
    });
  }



}

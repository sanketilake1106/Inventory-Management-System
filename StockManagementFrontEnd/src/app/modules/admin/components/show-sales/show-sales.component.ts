import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { Papa } from 'ngx-papaparse';
import { AdminService } from '../../services/admin.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

interface ProductSaleElement {
  id: number;
  saleProductName: string;
  saleProductQty: number;
  saleProductDate: string;
  productUnitPrice: number;
  customer: {
      customerId: number;
      customerName: string;
      customerEmail: string;
      customerContact: string;
      customerAddress: string;
  },
  saleProductAmount: number;
}


@Component({
  selector: 'app-show-sales',
  templateUrl: './show-sales.component.html',
  styleUrls: ['./show-sales.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ShowSalesComponent implements OnInit {
  displayedColumns: string[] = [
    'id',
    'saleProductName',
    'saleProductQty',
    'saleProductDate',
    'productUnitPrice',
    'customer',
    'saleProductAmount'
  ];
  productSale: ProductSaleElement[] = []
  expandedElement: ProductSaleElement | null | undefined;
  resultsLength = 0;


  constructor(
    private papa: Papa,
    private service: AdminService
  ) { }

  dataSource = new MatTableDataSource(this.productSale);

    ngOnInit(): void {
    this.service.getAllSalesProduct().subscribe(res=>{
      // this.productSale = res;
      this.dataSource.data = res;
      console.log(this.dataSource.data);

      let index: number = 1;
      this.dataSource.data.forEach(p=>{
        p.id = index++;
      });
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    this.calculatePurchaseNetAmount();
  }

  calculatePurchaseNetAmount(){
    let totalNetAmount = 0;
    this.dataSource.filteredData.forEach(element => {
      totalNetAmount += element.saleProductAmount;
    });
    return totalNetAmount;
  }

  downloadPDF() {
    const doc = new jsPDF();
    doc.text('Purchase List', 10, 10);
    (doc as any).autoTable({
      head: [['No.', 'Product Name', 'Product Price',
       'Total Purchase Qty', 'Purchase Date', 'Purchase Date', 'Purchase Amount']],
      body: this.dataSource.data.map(element => [
        element.id,
        element.saleProductName,
        element.saleProductQty,
        element.productUnitPrice,
        element.saleProductAmount
      ])
    });
    doc.save('purchase-list.pdf');
  }

  downloadCSV() {
    const csvData = this.dataSource.data.map(element => ({
      'Sr.No': element.id,
      'Product Name': element.saleProductName,
      'Product Price': element.productUnitPrice,
      'Purchase Quantity': element.saleProductQty,
      'Sale Date': element.saleProductDate,
      'Purchase Amount': element.saleProductAmount,
    }));
    const csv = this.papa.unparse(csvData);
    const blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    const url = URL.createObjectURL(blob);
    link.setAttribute('href', url);
    link.setAttribute('download', 'purchase-list.csv');
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


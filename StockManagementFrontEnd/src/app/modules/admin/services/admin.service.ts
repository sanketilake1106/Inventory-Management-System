import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import BASE_URL from '../../../auth/services/helper';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  public addPurchase(data: any): Observable<any> {
    return this.http.post(BASE_URL + "/purchase/purchaseStock", data);
  }

  public saleProduct(data: any): Observable<any> {
    return this.http.post(BASE_URL + "/sales/saleStock", data);
  }

  public addProduct(data: any): Observable<any> {
    return this.http.post(BASE_URL + "/product/addProduct", data);
  }

  public addSupplier(data: any): Observable<any> {
    return this.http.post(BASE_URL + "/supplier/addSupplier", data);
  }

  public getAllProductsCategory(): Observable<any> {
    return this.http.get(BASE_URL + "/product/getProductsCategory");
  }

  public getAllProducts(): Observable<any> {
    return this.http.get(BASE_URL + "/product/getProducts");
  }

  public getProductByProductCategory(category:any): Observable<any> {
    return this.http.get(BASE_URL + `/product/getProductByCategory/${category}`);
  }


  public getSignleProductByProductId(id:any): Observable<any> {
    return this.http.get(BASE_URL + `/product/getProduct/${id}`);
  }

  public getLastProduct(): Observable<any> {
    return this.http.get(BASE_URL + "/product/getLastProduct");
  }

  public getLastCustomer(): Observable<any> {
    return this.http.get(BASE_URL + "/customer/getLastCustomer");
  }

  public getCustomerById(id:any): Observable<any> {
    return this.http.get(BASE_URL + `/customer/getCustomer/${id}`);
  }

  public getLastSupplier(): Observable<any> {
    return this.http.get(BASE_URL + "/supplier/getLastSupplier");
  }

  public getLastPurchase(): Observable<any> {
    return this.http.get(BASE_URL + "/purchase/getLastPurchase");
  }

  public getSuppliers(): Observable<any> {
    return this.http.get(BASE_URL + "/supplier/getAllSuppliers");
  }

  public getSuppliersByName(name: any): Observable<any> {
    return this.http.post(BASE_URL + "/supplier/getSuppliersByName", name);
  }

  public getSuppliersByContact(contact: any): Observable<any> {
    return this.http.post(BASE_URL + "/supplier/getSupplierByContact", contact);
  }

  public getAllPurchaseProducts():Observable<any>{
    return this.http.get(BASE_URL + "/purchase/getAllPurchaseProducts");
  }

  public getAllSalesProduct():Observable<any>{
    return this.http.get(BASE_URL + "/sales/getAllSalesProduct");
  }

  public getPurchaseById(id:any):Observable<any>{
    return this.http.get(BASE_URL + `/purchase/getPurchaseById/${id}`);
  }


  public getStockByProductId(id:any):Observable<any>{
    return this.http.get(BASE_URL + `/stock/getStockByProductId/${id}`);
  }

  public getAllCustomers():Observable<any>{
    return this.http.get(BASE_URL + "/customer/getCustomers");
  }

  public getAllSaleProductByCustomerId(id:any):Observable<any>{
    return this.http.get(BASE_URL + `/sales/getAllSaleProductByCustomerId/${id}`);
  }

  public getTotalOverallSale():Observable<any>{
    return this.http.get(BASE_URL + "/sales/getTotalOverallSale");
  }

  public getTotalSaleAmount():Observable<any>{
    return this.http.get(BASE_URL + "/sales/getTotalSaleAmount");
  }

  public getTotalPurchaseQty():Observable<any>{
    return this.http.get(BASE_URL + "/purchase/getTotalPurchaseQty");
  }

  public getTotalPurchaseAmount():Observable<any>{
    return this.http.get(BASE_URL + "/purchase/getTotalPurchaseAmount");
  }

  public getTotalStockQty():Observable<any>{
    return this.http.get(BASE_URL + "/stock/getTotalStockQty");
  }

  public getTotalStockAmount():Observable<any>{
    return this.http.get(BASE_URL + "/stock/getTotalStockAmount");
  }


}

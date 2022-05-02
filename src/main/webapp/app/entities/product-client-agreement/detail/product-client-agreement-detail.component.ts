import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductClientAgreement } from '../product-client-agreement.model';

@Component({
  selector: 'jhi-product-client-agreement-detail',
  templateUrl: './product-client-agreement-detail.component.html',
})
export class ProductClientAgreementDetailComponent implements OnInit {
  productClientAgreement: IProductClientAgreement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productClientAgreement }) => {
      this.productClientAgreement = productClientAgreement;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

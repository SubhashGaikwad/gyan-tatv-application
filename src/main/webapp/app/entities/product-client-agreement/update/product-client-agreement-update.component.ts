import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';
import { ProductClientAgreementService } from '../service/product-client-agreement.service';
import { IProduct } from 'app/entities/product/product.model';
import { ProductService } from 'app/entities/product/service/product.service';
import { IClientDetails } from 'app/entities/client-details/client-details.model';
import { ClientDetailsService } from 'app/entities/client-details/service/client-details.service';
import { PenaltyTerms } from 'app/entities/enumerations/penalty-terms.model';

@Component({
  selector: 'jhi-product-client-agreement-update',
  templateUrl: './product-client-agreement-update.component.html',
})
export class ProductClientAgreementUpdateComponent implements OnInit {
  isSaving = false;
  penaltyTermsValues = Object.keys(PenaltyTerms);

  productsSharedCollection: IProduct[] = [];
  clientDetailsSharedCollection: IClientDetails[] = [];

  editForm = this.fb.group({
    id: [],
    price: [],
    creditPeriod: [],
    penalty: [],
    penaltyTerms: [],
    notes: [],
    image: [],
    agreementPeriod: [],
    termAndConditions: [],
    description: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    lastModified: [],
    lastModifiedBy: [],
    product: [],
    clientDetails: [],
  });

  constructor(
    protected productClientAgreementService: ProductClientAgreementService,
    protected productService: ProductService,
    protected clientDetailsService: ClientDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productClientAgreement }) => {
      this.updateForm(productClientAgreement);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productClientAgreement = this.createFromForm();
    if (productClientAgreement.id !== undefined) {
      this.subscribeToSaveResponse(this.productClientAgreementService.update(productClientAgreement));
    } else {
      this.subscribeToSaveResponse(this.productClientAgreementService.create(productClientAgreement));
    }
  }

  trackProductById(_index: number, item: IProduct): number {
    return item.id!;
  }

  trackClientDetailsById(_index: number, item: IClientDetails): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductClientAgreement>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(productClientAgreement: IProductClientAgreement): void {
    this.editForm.patchValue({
      id: productClientAgreement.id,
      price: productClientAgreement.price,
      creditPeriod: productClientAgreement.creditPeriod,
      penalty: productClientAgreement.penalty,
      penaltyTerms: productClientAgreement.penaltyTerms,
      notes: productClientAgreement.notes,
      image: productClientAgreement.image,
      agreementPeriod: productClientAgreement.agreementPeriod,
      termAndConditions: productClientAgreement.termAndConditions,
      description: productClientAgreement.description,
      freeField1: productClientAgreement.freeField1,
      freeField2: productClientAgreement.freeField2,
      freeField3: productClientAgreement.freeField3,
      freeField4: productClientAgreement.freeField4,
      lastModified: productClientAgreement.lastModified,
      lastModifiedBy: productClientAgreement.lastModifiedBy,
      product: productClientAgreement.product,
      clientDetails: productClientAgreement.clientDetails,
    });

    this.productsSharedCollection = this.productService.addProductToCollectionIfMissing(
      this.productsSharedCollection,
      productClientAgreement.product
    );
    this.clientDetailsSharedCollection = this.clientDetailsService.addClientDetailsToCollectionIfMissing(
      this.clientDetailsSharedCollection,
      productClientAgreement.clientDetails
    );
  }

  protected loadRelationshipsOptions(): void {
    this.productService
      .query()
      .pipe(map((res: HttpResponse<IProduct[]>) => res.body ?? []))
      .pipe(
        map((products: IProduct[]) => this.productService.addProductToCollectionIfMissing(products, this.editForm.get('product')!.value))
      )
      .subscribe((products: IProduct[]) => (this.productsSharedCollection = products));

    this.clientDetailsService
      .query()
      .pipe(map((res: HttpResponse<IClientDetails[]>) => res.body ?? []))
      .pipe(
        map((clientDetails: IClientDetails[]) =>
          this.clientDetailsService.addClientDetailsToCollectionIfMissing(clientDetails, this.editForm.get('clientDetails')!.value)
        )
      )
      .subscribe((clientDetails: IClientDetails[]) => (this.clientDetailsSharedCollection = clientDetails));
  }

  protected createFromForm(): IProductClientAgreement {
    return {
      ...new ProductClientAgreement(),
      id: this.editForm.get(['id'])!.value,
      price: this.editForm.get(['price'])!.value,
      creditPeriod: this.editForm.get(['creditPeriod'])!.value,
      penalty: this.editForm.get(['penalty'])!.value,
      penaltyTerms: this.editForm.get(['penaltyTerms'])!.value,
      notes: this.editForm.get(['notes'])!.value,
      image: this.editForm.get(['image'])!.value,
      agreementPeriod: this.editForm.get(['agreementPeriod'])!.value,
      termAndConditions: this.editForm.get(['termAndConditions'])!.value,
      description: this.editForm.get(['description'])!.value,
      freeField1: this.editForm.get(['freeField1'])!.value,
      freeField2: this.editForm.get(['freeField2'])!.value,
      freeField3: this.editForm.get(['freeField3'])!.value,
      freeField4: this.editForm.get(['freeField4'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      product: this.editForm.get(['product'])!.value,
      clientDetails: this.editForm.get(['clientDetails'])!.value,
    };
  }
}

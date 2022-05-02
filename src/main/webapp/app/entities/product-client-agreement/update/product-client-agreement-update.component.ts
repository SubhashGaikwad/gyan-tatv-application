import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';
import { ProductClientAgreementService } from '../service/product-client-agreement.service';
import { PenaltyTerms } from 'app/entities/enumerations/penalty-terms.model';

@Component({
  selector: 'jhi-product-client-agreement-update',
  templateUrl: './product-client-agreement-update.component.html',
})
export class ProductClientAgreementUpdateComponent implements OnInit {
  isSaving = false;
  penaltyTermsValues = Object.keys(PenaltyTerms);

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
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    lastModified: [],
    lastModifiedBy: [],
  });

  constructor(
    protected productClientAgreementService: ProductClientAgreementService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productClientAgreement }) => {
      this.updateForm(productClientAgreement);
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
      freeField1: productClientAgreement.freeField1,
      freeField2: productClientAgreement.freeField2,
      freeField3: productClientAgreement.freeField3,
      freeField4: productClientAgreement.freeField4,
      lastModified: productClientAgreement.lastModified,
      lastModifiedBy: productClientAgreement.lastModifiedBy,
    });
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
      freeField1: this.editForm.get(['freeField1'])!.value,
      freeField2: this.editForm.get(['freeField2'])!.value,
      freeField3: this.editForm.get(['freeField3'])!.value,
      freeField4: this.editForm.get(['freeField4'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
    };
  }
}

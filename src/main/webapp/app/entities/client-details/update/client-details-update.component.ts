import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IClientDetails, ClientDetails } from '../client-details.model';
import { ClientDetailsService } from '../service/client-details.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IDistrict } from 'app/entities/district/district.model';
import { DistrictService } from 'app/entities/district/service/district.service';
import { ClientType } from 'app/entities/enumerations/client-type.model';
import { RegistrationLevel } from 'app/entities/enumerations/registration-level.model';

@Component({
  selector: 'jhi-client-details-update',
  templateUrl: './client-details-update.component.html',
})
export class ClientDetailsUpdateComponent implements OnInit {
  isSaving = false;
  clientTypeValues = Object.keys(ClientType);
  registrationLevelValues = Object.keys(RegistrationLevel);

  districtsCollection: IDistrict[] = [];

  editForm = this.fb.group({
    id: [],
    customerID: [],
    clientName: [null, [Validators.required]],
    clientType: [],
    mobileNo: [],
    email: [],
    pincode: [],
    billingAddress: [],
    companyName: [],
    companyContactNo: [],
    website: [],
    gstinNumber: [null, []],
    companyPan: [],
    pancardImage: [],
    pancardImageContentType: [],
    companyTan: [],
    tanImage: [],
    tanImageContentType: [],
    gstCertificateImage: [],
    gstCertificateImageContentType: [],
    cancelledChequeImage: [],
    cancelledChequeImageContentType: [],
    udyogAadharImage: [],
    udyogAadharImageContentType: [],
    description: [],
    nameOfBeneficiary: [],
    accountNumber: [],
    bankName: [],
    accountType: [],
    ifscCode: [],
    registrationCategory: [],
    registrationLevel: [],
    isApproved: [],
    isActivated: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    lastModified: [],
    lastModifiedBy: [],
    district: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected clientDetailsService: ClientDetailsService,
    protected districtService: DistrictService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientDetails }) => {
      this.updateForm(clientDetails);

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('gyanTatvApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientDetails = this.createFromForm();
    if (clientDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.clientDetailsService.update(clientDetails));
    } else {
      this.subscribeToSaveResponse(this.clientDetailsService.create(clientDetails));
    }
  }

  trackDistrictById(_index: number, item: IDistrict): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientDetails>>): void {
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

  protected updateForm(clientDetails: IClientDetails): void {
    this.editForm.patchValue({
      id: clientDetails.id,
      customerID: clientDetails.customerID,
      clientName: clientDetails.clientName,
      clientType: clientDetails.clientType,
      mobileNo: clientDetails.mobileNo,
      email: clientDetails.email,
      pincode: clientDetails.pincode,
      billingAddress: clientDetails.billingAddress,
      companyName: clientDetails.companyName,
      companyContactNo: clientDetails.companyContactNo,
      website: clientDetails.website,
      gstinNumber: clientDetails.gstinNumber,
      companyPan: clientDetails.companyPan,
      pancardImage: clientDetails.pancardImage,
      pancardImageContentType: clientDetails.pancardImageContentType,
      companyTan: clientDetails.companyTan,
      tanImage: clientDetails.tanImage,
      tanImageContentType: clientDetails.tanImageContentType,
      gstCertificateImage: clientDetails.gstCertificateImage,
      gstCertificateImageContentType: clientDetails.gstCertificateImageContentType,
      cancelledChequeImage: clientDetails.cancelledChequeImage,
      cancelledChequeImageContentType: clientDetails.cancelledChequeImageContentType,
      udyogAadharImage: clientDetails.udyogAadharImage,
      udyogAadharImageContentType: clientDetails.udyogAadharImageContentType,
      description: clientDetails.description,
      nameOfBeneficiary: clientDetails.nameOfBeneficiary,
      accountNumber: clientDetails.accountNumber,
      bankName: clientDetails.bankName,
      accountType: clientDetails.accountType,
      ifscCode: clientDetails.ifscCode,
      registrationCategory: clientDetails.registrationCategory,
      registrationLevel: clientDetails.registrationLevel,
      isApproved: clientDetails.isApproved,
      isActivated: clientDetails.isActivated,
      freeField1: clientDetails.freeField1,
      freeField2: clientDetails.freeField2,
      freeField3: clientDetails.freeField3,
      freeField4: clientDetails.freeField4,
      lastModified: clientDetails.lastModified,
      lastModifiedBy: clientDetails.lastModifiedBy,
      district: clientDetails.district,
    });

    this.districtsCollection = this.districtService.addDistrictToCollectionIfMissing(this.districtsCollection, clientDetails.district);
  }

  protected loadRelationshipsOptions(): void {
    this.districtService
      .query({ 'clientDetailsId.specified': 'false' })
      .pipe(map((res: HttpResponse<IDistrict[]>) => res.body ?? []))
      .pipe(
        map((districts: IDistrict[]) =>
          this.districtService.addDistrictToCollectionIfMissing(districts, this.editForm.get('district')!.value)
        )
      )
      .subscribe((districts: IDistrict[]) => (this.districtsCollection = districts));
  }

  protected createFromForm(): IClientDetails {
    return {
      ...new ClientDetails(),
      id: this.editForm.get(['id'])!.value,
      customerID: this.editForm.get(['customerID'])!.value,
      clientName: this.editForm.get(['clientName'])!.value,
      clientType: this.editForm.get(['clientType'])!.value,
      mobileNo: this.editForm.get(['mobileNo'])!.value,
      email: this.editForm.get(['email'])!.value,
      pincode: this.editForm.get(['pincode'])!.value,
      billingAddress: this.editForm.get(['billingAddress'])!.value,
      companyName: this.editForm.get(['companyName'])!.value,
      companyContactNo: this.editForm.get(['companyContactNo'])!.value,
      website: this.editForm.get(['website'])!.value,
      gstinNumber: this.editForm.get(['gstinNumber'])!.value,
      companyPan: this.editForm.get(['companyPan'])!.value,
      pancardImageContentType: this.editForm.get(['pancardImageContentType'])!.value,
      pancardImage: this.editForm.get(['pancardImage'])!.value,
      companyTan: this.editForm.get(['companyTan'])!.value,
      tanImageContentType: this.editForm.get(['tanImageContentType'])!.value,
      tanImage: this.editForm.get(['tanImage'])!.value,
      gstCertificateImageContentType: this.editForm.get(['gstCertificateImageContentType'])!.value,
      gstCertificateImage: this.editForm.get(['gstCertificateImage'])!.value,
      cancelledChequeImageContentType: this.editForm.get(['cancelledChequeImageContentType'])!.value,
      cancelledChequeImage: this.editForm.get(['cancelledChequeImage'])!.value,
      udyogAadharImageContentType: this.editForm.get(['udyogAadharImageContentType'])!.value,
      udyogAadharImage: this.editForm.get(['udyogAadharImage'])!.value,
      description: this.editForm.get(['description'])!.value,
      nameOfBeneficiary: this.editForm.get(['nameOfBeneficiary'])!.value,
      accountNumber: this.editForm.get(['accountNumber'])!.value,
      bankName: this.editForm.get(['bankName'])!.value,
      accountType: this.editForm.get(['accountType'])!.value,
      ifscCode: this.editForm.get(['ifscCode'])!.value,
      registrationCategory: this.editForm.get(['registrationCategory'])!.value,
      registrationLevel: this.editForm.get(['registrationLevel'])!.value,
      isApproved: this.editForm.get(['isApproved'])!.value,
      isActivated: this.editForm.get(['isActivated'])!.value,
      freeField1: this.editForm.get(['freeField1'])!.value,
      freeField2: this.editForm.get(['freeField2'])!.value,
      freeField3: this.editForm.get(['freeField3'])!.value,
      freeField4: this.editForm.get(['freeField4'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      district: this.editForm.get(['district'])!.value,
    };
  }
}

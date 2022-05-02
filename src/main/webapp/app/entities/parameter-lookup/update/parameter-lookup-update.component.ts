import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IParameterLookup, ParameterLookup } from '../parameter-lookup.model';
import { ParameterLookupService } from '../service/parameter-lookup.service';
import { ParameterType } from 'app/entities/enumerations/parameter-type.model';

@Component({
  selector: 'jhi-parameter-lookup-update',
  templateUrl: './parameter-lookup-update.component.html',
})
export class ParameterLookupUpdateComponent implements OnInit {
  isSaving = false;
  parameterTypeValues = Object.keys(ParameterType);

  editForm = this.fb.group({
    id: [],
    name: [],
    type: [],
    value: [],
    descriptions: [],
    isActive: [],
    lastModified: [],
    lastModifiedBy: [],
  });

  constructor(
    protected parameterLookupService: ParameterLookupService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parameterLookup }) => {
      this.updateForm(parameterLookup);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parameterLookup = this.createFromForm();
    if (parameterLookup.id !== undefined) {
      this.subscribeToSaveResponse(this.parameterLookupService.update(parameterLookup));
    } else {
      this.subscribeToSaveResponse(this.parameterLookupService.create(parameterLookup));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParameterLookup>>): void {
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

  protected updateForm(parameterLookup: IParameterLookup): void {
    this.editForm.patchValue({
      id: parameterLookup.id,
      name: parameterLookup.name,
      type: parameterLookup.type,
      value: parameterLookup.value,
      descriptions: parameterLookup.descriptions,
      isActive: parameterLookup.isActive,
      lastModified: parameterLookup.lastModified,
      lastModifiedBy: parameterLookup.lastModifiedBy,
    });
  }

  protected createFromForm(): IParameterLookup {
    return {
      ...new ParameterLookup(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      type: this.editForm.get(['type'])!.value,
      value: this.editForm.get(['value'])!.value,
      descriptions: this.editForm.get(['descriptions'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
    };
  }
}

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ProductClientAgreementService } from '../service/product-client-agreement.service';
import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';

import { ProductClientAgreementUpdateComponent } from './product-client-agreement-update.component';

describe('ProductClientAgreement Management Update Component', () => {
  let comp: ProductClientAgreementUpdateComponent;
  let fixture: ComponentFixture<ProductClientAgreementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let productClientAgreementService: ProductClientAgreementService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ProductClientAgreementUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ProductClientAgreementUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProductClientAgreementUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    productClientAgreementService = TestBed.inject(ProductClientAgreementService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const productClientAgreement: IProductClientAgreement = { id: 456 };

      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(productClientAgreement));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ProductClientAgreement>>();
      const productClientAgreement = { id: 123 };
      jest.spyOn(productClientAgreementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: productClientAgreement }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(productClientAgreementService.update).toHaveBeenCalledWith(productClientAgreement);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ProductClientAgreement>>();
      const productClientAgreement = new ProductClientAgreement();
      jest.spyOn(productClientAgreementService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: productClientAgreement }));
      saveSubject.complete();

      // THEN
      expect(productClientAgreementService.create).toHaveBeenCalledWith(productClientAgreement);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ProductClientAgreement>>();
      const productClientAgreement = { id: 123 };
      jest.spyOn(productClientAgreementService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(productClientAgreementService.update).toHaveBeenCalledWith(productClientAgreement);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

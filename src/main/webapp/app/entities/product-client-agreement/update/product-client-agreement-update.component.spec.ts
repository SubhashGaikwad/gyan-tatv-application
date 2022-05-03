import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ProductClientAgreementService } from '../service/product-client-agreement.service';
import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';
import { IProduct } from 'app/entities/product/product.model';
import { ProductService } from 'app/entities/product/service/product.service';
import { IClientDetails } from 'app/entities/client-details/client-details.model';
import { ClientDetailsService } from 'app/entities/client-details/service/client-details.service';

import { ProductClientAgreementUpdateComponent } from './product-client-agreement-update.component';

describe('ProductClientAgreement Management Update Component', () => {
  let comp: ProductClientAgreementUpdateComponent;
  let fixture: ComponentFixture<ProductClientAgreementUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let productClientAgreementService: ProductClientAgreementService;
  let productService: ProductService;
  let clientDetailsService: ClientDetailsService;

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
    productService = TestBed.inject(ProductService);
    clientDetailsService = TestBed.inject(ClientDetailsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Product query and add missing value', () => {
      const productClientAgreement: IProductClientAgreement = { id: 456 };
      const product: IProduct = { id: 42756 };
      productClientAgreement.product = product;

      const productCollection: IProduct[] = [{ id: 12614 }];
      jest.spyOn(productService, 'query').mockReturnValue(of(new HttpResponse({ body: productCollection })));
      const additionalProducts = [product];
      const expectedCollection: IProduct[] = [...additionalProducts, ...productCollection];
      jest.spyOn(productService, 'addProductToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      expect(productService.query).toHaveBeenCalled();
      expect(productService.addProductToCollectionIfMissing).toHaveBeenCalledWith(productCollection, ...additionalProducts);
      expect(comp.productsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call ClientDetails query and add missing value', () => {
      const productClientAgreement: IProductClientAgreement = { id: 456 };
      const clientDetails: IClientDetails = { id: 50286 };
      productClientAgreement.clientDetails = clientDetails;

      const clientDetailsCollection: IClientDetails[] = [{ id: 44916 }];
      jest.spyOn(clientDetailsService, 'query').mockReturnValue(of(new HttpResponse({ body: clientDetailsCollection })));
      const additionalClientDetails = [clientDetails];
      const expectedCollection: IClientDetails[] = [...additionalClientDetails, ...clientDetailsCollection];
      jest.spyOn(clientDetailsService, 'addClientDetailsToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      expect(clientDetailsService.query).toHaveBeenCalled();
      expect(clientDetailsService.addClientDetailsToCollectionIfMissing).toHaveBeenCalledWith(
        clientDetailsCollection,
        ...additionalClientDetails
      );
      expect(comp.clientDetailsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const productClientAgreement: IProductClientAgreement = { id: 456 };
      const product: IProduct = { id: 95871 };
      productClientAgreement.product = product;
      const clientDetails: IClientDetails = { id: 77393 };
      productClientAgreement.clientDetails = clientDetails;

      activatedRoute.data = of({ productClientAgreement });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(productClientAgreement));
      expect(comp.productsSharedCollection).toContain(product);
      expect(comp.clientDetailsSharedCollection).toContain(clientDetails);
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

  describe('Tracking relationships identifiers', () => {
    describe('trackProductById', () => {
      it('Should return tracked Product primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackProductById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackClientDetailsById', () => {
      it('Should return tracked ClientDetails primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackClientDetailsById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});

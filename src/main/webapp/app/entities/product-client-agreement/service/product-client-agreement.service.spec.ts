import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { PenaltyTerms } from 'app/entities/enumerations/penalty-terms.model';
import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';

import { ProductClientAgreementService } from './product-client-agreement.service';

describe('ProductClientAgreement Service', () => {
  let service: ProductClientAgreementService;
  let httpMock: HttpTestingController;
  let elemDefault: IProductClientAgreement;
  let expectedResult: IProductClientAgreement | IProductClientAgreement[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ProductClientAgreementService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      price: 0,
      creditPeriod: 0,
      penalty: 0,
      penaltyTerms: PenaltyTerms.DAILY,
      notes: 'AAAAAAA',
      image: 'AAAAAAA',
      agreementPeriod: 0,
      termAndConditions: 'AAAAAAA',
      description: 'AAAAAAA',
      freeField1: 'AAAAAAA',
      freeField2: 'AAAAAAA',
      freeField3: 'AAAAAAA',
      freeField4: 'AAAAAAA',
      lastModified: 'AAAAAAA',
      lastModifiedBy: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a ProductClientAgreement', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new ProductClientAgreement()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ProductClientAgreement', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          price: 1,
          creditPeriod: 1,
          penalty: 1,
          penaltyTerms: 'BBBBBB',
          notes: 'BBBBBB',
          image: 'BBBBBB',
          agreementPeriod: 1,
          termAndConditions: 'BBBBBB',
          description: 'BBBBBB',
          freeField1: 'BBBBBB',
          freeField2: 'BBBBBB',
          freeField3: 'BBBBBB',
          freeField4: 'BBBBBB',
          lastModified: 'BBBBBB',
          lastModifiedBy: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ProductClientAgreement', () => {
      const patchObject = Object.assign(
        {
          price: 1,
          creditPeriod: 1,
          penaltyTerms: 'BBBBBB',
          agreementPeriod: 1,
          termAndConditions: 'BBBBBB',
          freeField3: 'BBBBBB',
        },
        new ProductClientAgreement()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ProductClientAgreement', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          price: 1,
          creditPeriod: 1,
          penalty: 1,
          penaltyTerms: 'BBBBBB',
          notes: 'BBBBBB',
          image: 'BBBBBB',
          agreementPeriod: 1,
          termAndConditions: 'BBBBBB',
          description: 'BBBBBB',
          freeField1: 'BBBBBB',
          freeField2: 'BBBBBB',
          freeField3: 'BBBBBB',
          freeField4: 'BBBBBB',
          lastModified: 'BBBBBB',
          lastModifiedBy: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a ProductClientAgreement', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addProductClientAgreementToCollectionIfMissing', () => {
      it('should add a ProductClientAgreement to an empty array', () => {
        const productClientAgreement: IProductClientAgreement = { id: 123 };
        expectedResult = service.addProductClientAgreementToCollectionIfMissing([], productClientAgreement);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(productClientAgreement);
      });

      it('should not add a ProductClientAgreement to an array that contains it', () => {
        const productClientAgreement: IProductClientAgreement = { id: 123 };
        const productClientAgreementCollection: IProductClientAgreement[] = [
          {
            ...productClientAgreement,
          },
          { id: 456 },
        ];
        expectedResult = service.addProductClientAgreementToCollectionIfMissing(productClientAgreementCollection, productClientAgreement);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ProductClientAgreement to an array that doesn't contain it", () => {
        const productClientAgreement: IProductClientAgreement = { id: 123 };
        const productClientAgreementCollection: IProductClientAgreement[] = [{ id: 456 }];
        expectedResult = service.addProductClientAgreementToCollectionIfMissing(productClientAgreementCollection, productClientAgreement);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(productClientAgreement);
      });

      it('should add only unique ProductClientAgreement to an array', () => {
        const productClientAgreementArray: IProductClientAgreement[] = [{ id: 123 }, { id: 456 }, { id: 18791 }];
        const productClientAgreementCollection: IProductClientAgreement[] = [{ id: 123 }];
        expectedResult = service.addProductClientAgreementToCollectionIfMissing(
          productClientAgreementCollection,
          ...productClientAgreementArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const productClientAgreement: IProductClientAgreement = { id: 123 };
        const productClientAgreement2: IProductClientAgreement = { id: 456 };
        expectedResult = service.addProductClientAgreementToCollectionIfMissing([], productClientAgreement, productClientAgreement2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(productClientAgreement);
        expect(expectedResult).toContain(productClientAgreement2);
      });

      it('should accept null and undefined values', () => {
        const productClientAgreement: IProductClientAgreement = { id: 123 };
        expectedResult = service.addProductClientAgreementToCollectionIfMissing([], null, productClientAgreement, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(productClientAgreement);
      });

      it('should return initial array if no ProductClientAgreement is added', () => {
        const productClientAgreementCollection: IProductClientAgreement[] = [{ id: 123 }];
        expectedResult = service.addProductClientAgreementToCollectionIfMissing(productClientAgreementCollection, undefined, null);
        expect(expectedResult).toEqual(productClientAgreementCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ClientType } from 'app/entities/enumerations/client-type.model';
import { RegistrationLevel } from 'app/entities/enumerations/registration-level.model';
import { IClientDetails, ClientDetails } from '../client-details.model';

import { ClientDetailsService } from './client-details.service';

describe('ClientDetails Service', () => {
  let service: ClientDetailsService;
  let httpMock: HttpTestingController;
  let elemDefault: IClientDetails;
  let expectedResult: IClientDetails | IClientDetails[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ClientDetailsService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      customerID: 'AAAAAAA',
      clientName: 'AAAAAAA',
      clientType: ClientType.SUPPLIER,
      mobileNo: 'AAAAAAA',
      email: 'AAAAAAA',
      pincode: 0,
      billingAddress: 'AAAAAAA',
      companyName: 'AAAAAAA',
      companyContactNo: 'AAAAAAA',
      website: 'AAAAAAA',
      gstinNumber: 'AAAAAAA',
      companyPan: 'AAAAAAA',
      pancardImageContentType: 'image/png',
      pancardImage: 'AAAAAAA',
      companyTan: 'AAAAAAA',
      tanImageContentType: 'image/png',
      tanImage: 'AAAAAAA',
      gstCertificateImageContentType: 'image/png',
      gstCertificateImage: 'AAAAAAA',
      cancelledChequeImageContentType: 'image/png',
      cancelledChequeImage: 'AAAAAAA',
      udyogAadharImageContentType: 'image/png',
      udyogAadharImage: 'AAAAAAA',
      description: 'AAAAAAA',
      nameOfBeneficiary: 'AAAAAAA',
      accountNumber: 'AAAAAAA',
      bankName: 'AAAAAAA',
      accountType: 'AAAAAAA',
      ifscCode: 'AAAAAAA',
      registrationCategory: 'AAAAAAA',
      registrationLevel: RegistrationLevel.STATE,
      isApproved: false,
      isActivated: false,
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

    it('should create a ClientDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new ClientDetails()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClientDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          customerID: 'BBBBBB',
          clientName: 'BBBBBB',
          clientType: 'BBBBBB',
          mobileNo: 'BBBBBB',
          email: 'BBBBBB',
          pincode: 1,
          billingAddress: 'BBBBBB',
          companyName: 'BBBBBB',
          companyContactNo: 'BBBBBB',
          website: 'BBBBBB',
          gstinNumber: 'BBBBBB',
          companyPan: 'BBBBBB',
          pancardImage: 'BBBBBB',
          companyTan: 'BBBBBB',
          tanImage: 'BBBBBB',
          gstCertificateImage: 'BBBBBB',
          cancelledChequeImage: 'BBBBBB',
          udyogAadharImage: 'BBBBBB',
          description: 'BBBBBB',
          nameOfBeneficiary: 'BBBBBB',
          accountNumber: 'BBBBBB',
          bankName: 'BBBBBB',
          accountType: 'BBBBBB',
          ifscCode: 'BBBBBB',
          registrationCategory: 'BBBBBB',
          registrationLevel: 'BBBBBB',
          isApproved: true,
          isActivated: true,
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

    it('should partial update a ClientDetails', () => {
      const patchObject = Object.assign(
        {
          customerID: 'BBBBBB',
          clientName: 'BBBBBB',
          email: 'BBBBBB',
          pincode: 1,
          companyContactNo: 'BBBBBB',
          companyPan: 'BBBBBB',
          tanImage: 'BBBBBB',
          cancelledChequeImage: 'BBBBBB',
          udyogAadharImage: 'BBBBBB',
          nameOfBeneficiary: 'BBBBBB',
          accountNumber: 'BBBBBB',
          accountType: 'BBBBBB',
          ifscCode: 'BBBBBB',
          registrationLevel: 'BBBBBB',
          isActivated: true,
          freeField1: 'BBBBBB',
          freeField2: 'BBBBBB',
          lastModifiedBy: 'BBBBBB',
        },
        new ClientDetails()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClientDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          customerID: 'BBBBBB',
          clientName: 'BBBBBB',
          clientType: 'BBBBBB',
          mobileNo: 'BBBBBB',
          email: 'BBBBBB',
          pincode: 1,
          billingAddress: 'BBBBBB',
          companyName: 'BBBBBB',
          companyContactNo: 'BBBBBB',
          website: 'BBBBBB',
          gstinNumber: 'BBBBBB',
          companyPan: 'BBBBBB',
          pancardImage: 'BBBBBB',
          companyTan: 'BBBBBB',
          tanImage: 'BBBBBB',
          gstCertificateImage: 'BBBBBB',
          cancelledChequeImage: 'BBBBBB',
          udyogAadharImage: 'BBBBBB',
          description: 'BBBBBB',
          nameOfBeneficiary: 'BBBBBB',
          accountNumber: 'BBBBBB',
          bankName: 'BBBBBB',
          accountType: 'BBBBBB',
          ifscCode: 'BBBBBB',
          registrationCategory: 'BBBBBB',
          registrationLevel: 'BBBBBB',
          isApproved: true,
          isActivated: true,
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

    it('should delete a ClientDetails', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addClientDetailsToCollectionIfMissing', () => {
      it('should add a ClientDetails to an empty array', () => {
        const clientDetails: IClientDetails = { id: 123 };
        expectedResult = service.addClientDetailsToCollectionIfMissing([], clientDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientDetails);
      });

      it('should not add a ClientDetails to an array that contains it', () => {
        const clientDetails: IClientDetails = { id: 123 };
        const clientDetailsCollection: IClientDetails[] = [
          {
            ...clientDetails,
          },
          { id: 456 },
        ];
        expectedResult = service.addClientDetailsToCollectionIfMissing(clientDetailsCollection, clientDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClientDetails to an array that doesn't contain it", () => {
        const clientDetails: IClientDetails = { id: 123 };
        const clientDetailsCollection: IClientDetails[] = [{ id: 456 }];
        expectedResult = service.addClientDetailsToCollectionIfMissing(clientDetailsCollection, clientDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientDetails);
      });

      it('should add only unique ClientDetails to an array', () => {
        const clientDetailsArray: IClientDetails[] = [{ id: 123 }, { id: 456 }, { id: 44381 }];
        const clientDetailsCollection: IClientDetails[] = [{ id: 123 }];
        expectedResult = service.addClientDetailsToCollectionIfMissing(clientDetailsCollection, ...clientDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const clientDetails: IClientDetails = { id: 123 };
        const clientDetails2: IClientDetails = { id: 456 };
        expectedResult = service.addClientDetailsToCollectionIfMissing([], clientDetails, clientDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientDetails);
        expect(expectedResult).toContain(clientDetails2);
      });

      it('should accept null and undefined values', () => {
        const clientDetails: IClientDetails = { id: 123 };
        expectedResult = service.addClientDetailsToCollectionIfMissing([], null, clientDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientDetails);
      });

      it('should return initial array if no ClientDetails is added', () => {
        const clientDetailsCollection: IClientDetails[] = [{ id: 123 }];
        expectedResult = service.addClientDetailsToCollectionIfMissing(clientDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(clientDetailsCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

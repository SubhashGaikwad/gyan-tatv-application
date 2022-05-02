import { ParameterType } from 'app/entities/enumerations/parameter-type.model';

export interface IParameterLookup {
  id?: number;
  name?: string | null;
  type?: ParameterType | null;
  value?: string | null;
  descriptions?: string | null;
  isActive?: boolean | null;
  lastModified?: string | null;
  lastModifiedBy?: string | null;
}

export class ParameterLookup implements IParameterLookup {
  constructor(
    public id?: number,
    public name?: string | null,
    public type?: ParameterType | null,
    public value?: string | null,
    public descriptions?: string | null,
    public isActive?: boolean | null,
    public lastModified?: string | null,
    public lastModifiedBy?: string | null
  ) {
    this.isActive = this.isActive ?? false;
  }
}

export function getParameterLookupIdentifier(parameterLookup: IParameterLookup): number | undefined {
  return parameterLookup.id;
}

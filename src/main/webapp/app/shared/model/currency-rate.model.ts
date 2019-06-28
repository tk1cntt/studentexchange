import { Moment } from 'moment';

export const enum CurrencyType {
  USD = 'USD',
  EUR = 'EUR',
  CNY = 'CNY',
  JPY = 'JPY',
  KPW = 'KPW'
}

export interface ICurrencyRate {
  id?: number;
  currency?: CurrencyType;
  rate?: number;
  createAt?: Moment;
}

export const defaultValue: Readonly<ICurrencyRate> = {};

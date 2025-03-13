import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInteceptorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
};

import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInteceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = localStorage.getItem("token");
  
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${authToken}`
    }
  });

  return next(req);
};

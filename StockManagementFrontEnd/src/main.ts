import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { registerLicense } from "@syncfusion/ej2-base";

registerLicense("ORg4AjUWIQA/Gnt2VVhhQIFac11JW3xNYVF2R2FJe1RzdF9DZkwgOX1dQ19hSXtTcEVhWndceXFdQmY=");


platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));


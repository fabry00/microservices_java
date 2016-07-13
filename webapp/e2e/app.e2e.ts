import { WebappPage } from './app.po';

describe('webapp App', function() {
  let page: WebappPage;

  beforeEach(() => {
    page = new WebappPage();
  })

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('webapp works!');
  });
});

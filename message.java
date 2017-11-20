public class message 
{
  
  public String text;
  public reply r;
  public entity from;
  public group destgroup;

  

  public message()
  {
    //r = new reply();
    r = new reply();
    r.set_message(this);
    //System.out.println("I'm HERE");
  }

  public entity get_from()
  {
  	return this.from;
  }

  public String get_text()
  {
  	return this.text;
  }

  public void set_from(entity e)
  {
    this.from = e;
  }

  public group get_destgroup()
  {
  	return this.destgroup;
  }


  public void set_text(String text)
  {
  	this.text = text;
  }

  public void set_destgroup(group g)
  {
  	this.destgroup = g;
  }




}
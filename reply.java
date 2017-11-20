public class reply
{
	public message m;
	public String text;
	public entity from;
	
	public void reply()
	{	
		
	}

	public void set_text(String text)
	{
		this.text = text;
	}
	public void set_message(message m)
	{
		this.m = m;
	}
	public String get_text()
	{
		return this.text;
	}
	public message get_message()
	{
		return this.m;
	}
	public void set_from(entity e)
	{
		this.from = e;
	}
	public entity get_from()
	{
		return this.from;
	}

}
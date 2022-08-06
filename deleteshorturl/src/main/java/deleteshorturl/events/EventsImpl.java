package deleteshorturl.events;

import shorturls.dao.Deleter;

public class EventsImpl implements Events{

	private final Deleter deleter;

	public EventsImpl(Deleter dao){
		this.deleter=dao;
	}

	@Override
	public void delete(String shortPath) {
		deleter.deleteById(shortPath);
	}




}

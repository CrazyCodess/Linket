/*将String类型解析为Date类型.      
  parseDate('2006-1-1') return new Date(2006,0,1)      
  parseDate(' 2006-1-1 ') return new Date(2006,0,1)      
  parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16)      
  parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16);      
  parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254)      
  parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254)      
  parseDate('不正确的格式') retrun null      
*/
function parseDate(str) {
	if (typeof str == 'string') {
		
		var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
		if (results && results.length > 3){
			return new Date(
					parseInt(results[1],10),
					parseInt(results[2],10) - 1,
					parseInt(results[3],10)
					);
		}
		
		results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
		if (results && results.length > 6){
			return new Date(
					parseInt(results[1],10),
					parseInt(results[2],10) - 1,
					parseInt(results[3],10), 
					parseInt(results[4],10),
					parseInt(results[5],10), 
					parseInt(results[6],10));
		}
		
		results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
		if (results && results.length > 7){
			return new Date(
					parseInt(results[1],10), 
					parseInt(results[2],10) - 1,
					parseInt(results[3],10), 
					parseInt(results[4],10),
					parseInt(results[5],10), 
					parseInt(results[6],10),
					parseInt(results[7],10));
		}
	}
	return null;
}
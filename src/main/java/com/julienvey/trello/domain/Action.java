package com.julienvey.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action extends TrelloEntity {

    private String id;
    private String idMemberCreator;
    private Data data;
    private String type;
    private Date date;
    private MemberShort memberCreator;
    private MemberShort member;

    /* API */

    Board fetchBoard(Argument... args) {
        return trelloService.getActionBoard(id, args);
    }

    Card fetchCard(Argument... args) {
        return trelloService.getActionCard(id, args);
    }

    List<Entity> fetchEntities() {
        return trelloService.getActionEntities(id);
    }

    TList fetchTList(Argument... args) {
        return trelloService.getActionList(id, args);
    }

    Member fetchMember(Argument... args) {
        return trelloService.getActionMember(id, args);
    }

    Member fetchMemberCreator(Argument... args) {
        return trelloService.getActionMemberCreator(id, args);
    }

    Organization fetchOrganization(Argument... args) {
        return trelloService.getActionOrganization(id, args);
    }

    /* Accessors */
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMemberCreator() {
        return idMemberCreator;
    }

    public void setIdMemberCreator(String idMemberCreator) {
        this.idMemberCreator = idMemberCreator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MemberShort getMemberCreator() {
        return memberCreator;
    }

    public void setMemberCreator(MemberShort memberCreator) {
        this.memberCreator = memberCreator;
    }

    public MemberShort getMember() {
        return member;
    }

    public void setMember(MemberShort member) {
        this.member = member;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class MemberShort {
        private String id;
        private String avatarHash;
        private String fullName;
        private String initials;
        private String username;

        public String getAvatarHash() {
            return avatarHash;
        }

        public void setAvatarHash(String avatarHash) {
            this.avatarHash = avatarHash;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Data {

        private Board board;
        private TList list;
        private Card card;
        private AttachementShort attachment;
        private String text;
        private CheckList checklist;
        private CheckItem checkItem;
        private String idMember;
        private Old old;
        private TList listAfter;
        private TList listBefore;
        private Date dateLastEdited;

        public Date getDateLastEdited() {
            return dateLastEdited;
        }

        public void setDateLastEdited(Date dateLastEdited) {
            this.dateLastEdited = dateLastEdited;
        }

        public TList getListAfter() {
            return listAfter;
        }

        public void setListAfter(TList listAfter) {
            this.listAfter = listAfter;
        }

        public TList getListBefore() {
            return listBefore;
        }

        public void setListBefore(TList listBefore) {
            this.listBefore = listBefore;
        }

        public Board getBoard() {
            return board;
        }

        public void setBoard(Board board) {
            this.board = board;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public TList getList() {
            return list;
        }

        public void setList(TList list) {
            this.list = list;
        }

        public AttachementShort getAttachment() {
            return attachment;
        }

        public void setAttachment(AttachementShort attachment) {
            this.attachment = attachment;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public CheckList getChecklist() {
            return checklist;
        }

        public void setChecklist(CheckList checklist) {
            this.checklist = checklist;
        }

        public CheckItem getCheckItem() {
            return checkItem;
        }

        public void setCheckItem(CheckItem checkItem) {
            this.checkItem = checkItem;
        }

        public String getIdMember() {
            return idMember;
        }

        public void setIdMember(String idMember) {
            this.idMember = idMember;
        }

        public Old getOld() {
            return old;
        }

        public void setOld(Old old) {
            this.old = old;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class Old {
            private boolean closed;
            private Map<String, String> labelNames;
            private String desc;
            private String idList;

            public String getIdList() {
                return idList;
            }

            public void setIdList(String idList) {
                this.idList = idList;
            }

            public boolean isClosed() {
                return closed;
            }

            public void setClosed(boolean closed) {
                this.closed = closed;
            }

            public Map<String, String> getLabelNames() {
                return labelNames;
            }

            public void setLabelNames(Map<String, String> labelNames) {
                this.labelNames = labelNames;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class CheckItem {
            private String state;
            private String name;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class CheckList {
            private String name;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class AttachementShort {
            private String name;
            private String id;
            private String url;
            private String previewUrl;
            private String previewUrl2x;

            public String getPreviewUrl2x() {
                return previewUrl2x;
            }

            public void setPreviewUrl2x(String previewUrl2x) {
                this.previewUrl2x = previewUrl2x;
            }

            public String getPreviewUrl() {
                return previewUrl;
            }

            public void setPreviewUrl(String previewUrl) {
                this.previewUrl = previewUrl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    }
}
